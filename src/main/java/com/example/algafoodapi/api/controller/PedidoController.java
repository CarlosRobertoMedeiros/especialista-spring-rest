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
import com.example.algafoodapi.core.data.PageableTranslator;
import com.example.algafoodapi.dominio.exception.EntidadeNaoEncontradaException;
import com.example.algafoodapi.dominio.exception.NegocioException;
import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.modelo.Usuario;
import com.example.algafoodapi.dominio.repository.PedidoRepository;
import com.example.algafoodapi.dominio.filter.PedidoFilter;
import com.example.algafoodapi.dominio.service.EmissaoPedidoService;
import com.example.algafoodapi.infraestrutura.repository.spec.PedidoSpec;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por virgula ",
                              name = "campos", paramType = "query", type = "string")
    })
    @GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro,
                                             @PageableDefault(size = 10) Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(
                PedidoSpec.usandoFiltro(filtro), pageable);

        List<PedidoResumoModel> pedidosResumoModel = pedidoResumoModelAssemblerModelAssembler
                .toCollectionModel(pedidosPage.getContent());

        Page<PedidoResumoModel> pedidosResumoModelPage = new PageImpl<>(
                pedidosResumoModel, pageable, pedidosPage.getTotalElements());

        return pedidosResumoModelPage;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por virgula ",
                    name = "campos", paramType = "query", type = "string")
    })
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

    private Pageable traduzirPageable(Pageable apiPageable){
        Map<String,String> mapeamento = new HashMap<>();
        mapeamento.put("codigo","codigo");
        mapeamento.put("subtotal","subtotal");
        mapeamento.put("taxaFrete","taxaFrete");
        mapeamento.put("valorTotal","valorTotal");
        mapeamento.put("dataCriacao","dataCriacao");
        mapeamento.put("restaurante.nome","restaurante.nome");
        mapeamento.put("restaurante.id","restaurante.id");
        mapeamento.put("cliente.id","cliente.id");
        mapeamento.put("cliente.nome","cliente.nome");
        return PageableTranslator.translate(apiPageable, mapeamento);
    }


}
