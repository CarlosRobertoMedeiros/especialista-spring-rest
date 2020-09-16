package com.example.algafoodapi.api.v1.controller;
/*
 *  @criado em: 21/07/2020 - {06:55}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */


import com.example.algafoodapi.api.v1.assembler.PedidoInputDisassembler;
import com.example.algafoodapi.api.v1.assembler.PedidoModelAssembler;
import com.example.algafoodapi.api.v1.assembler.PedidoResumoModelAssembler;
import com.example.algafoodapi.api.v1.model.PedidoModel;
import com.example.algafoodapi.api.v1.model.PedidoResumoModel;
import com.example.algafoodapi.api.v1.model.input.PedidoInput;
import com.example.algafoodapi.core.data.PageWrapper;
import com.example.algafoodapi.core.data.PageableTranslator;
import com.example.algafoodapi.core.security.AlgaSecurity;
import com.example.algafoodapi.dominio.exception.EntidadeNaoEncontradaException;
import com.example.algafoodapi.dominio.exception.NegocioException;
import com.example.algafoodapi.dominio.filter.PedidoFilter;
import com.example.algafoodapi.dominio.modelo.Pedido;
import com.example.algafoodapi.dominio.modelo.Usuario;
import com.example.algafoodapi.dominio.repository.PedidoRepository;
import com.example.algafoodapi.dominio.service.EmissaoPedidoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import com.example.algafoodapi.infraestrutura.repository.spec.PedidoSpec;

@RestController
@RequestMapping(value = "/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler; //Mostra erro no Intelij porém, está certo

    @Autowired
    private AlgaSecurity algaSecurity;

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
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro,
                                                   @PageableDefault(size = 10) Pageable pageable) {
        Pageable pageableTraduzido = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(
                PedidoSpec.usandoFiltro(filtro), pageableTraduzido);

        pedidosPage = new PageWrapper<>(pedidosPage,pageable);

        return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
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

            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(algaSecurity.getUsuarioId());

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
