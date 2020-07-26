package com.example.algafoodapi.api.controller;
/*
 *  @criado em: 21/07/2020 - {06:55}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.api.assembler.PedidoInputDisassembler;
import com.example.algafoodapi.api.assembler.PedidoModelAssembler;
import com.example.algafoodapi.api.assembler.PedidoResumoModelAssembler;
import com.example.algafoodapi.api.model.PedidoModel;
import com.example.algafoodapi.api.model.PedidoResumoModel;
import com.example.algafoodapi.api.model.input.PedidoInput;
import com.example.algafoodapi.dominio.exception.EntidadeNaoEncontradaException;
import com.example.algafoodapi.dominio.exception.NegocioException;
import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.modelo.Usuario;
import com.example.algafoodapi.dominio.repository.PedidoRepository;
import com.example.algafoodapi.dominio.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssemblerModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> todosPedidos = pedidoRepository.findAll();
//        List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssemblerModelAssembler.toCollectionModel(todosPedidos);
//
//        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);
//
//        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider();
//        simpleFilterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//
//        if (StringUtils.isNotBlank(campos)){
//            simpleFilterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//        }
//        pedidosWrapper.setFilters(simpleFilterProvider);
//        return pedidosWrapper;
//    }

    @GetMapping
    public List<PedidoResumoModel> listar() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();
        return pedidoResumoModelAssemblerModelAssembler.toCollectionModel(todosPedidos);
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);

        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedidoService.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
}
