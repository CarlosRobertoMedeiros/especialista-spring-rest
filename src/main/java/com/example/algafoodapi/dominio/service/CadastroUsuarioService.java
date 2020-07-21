package com.example.algafoodapi.dominio.service;
/*
 *  @criado em: 19/07/2020 - {10:23}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import com.example.algafoodapi.dominio.exception.NegocioException;
import com.example.algafoodapi.dominio.exception.UsuarioNaoEncontradoException;
import com.example.algafoodapi.dominio.modelo.Grupo;
import com.example.algafoodapi.dominio.modelo.Restaurante;
import com.example.algafoodapi.dominio.modelo.Usuario;
import com.example.algafoodapi.dominio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroGrupoService grupoService;

    @Autowired
    private EntityManager manager;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        manager.detach(usuario);
        //usuarioRepository.detach(usuario); //Desatachar o usuário devido ao erro de exceção do
                                 // sistema evitando sincronização com o banco de dados para atualização
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)){
            throw new NegocioException(
                    String.format("Já existe um usuário cadastrado com o e-mail %s",usuario.getEmail()));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);

        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId){
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);

        usuario.removerGrupo(grupo);
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId){
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);

        usuario.adicionarGrupo(grupo);
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }




}
