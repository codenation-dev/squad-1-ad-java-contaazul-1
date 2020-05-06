import { http } from './config'

export default {
	listar: () => {
		return http.get('usuario/get')
	},

	login: (nome, senha) => {
		return http.get('login/get/' + nome + '/' + senha)
	},

	salvar: (usuario) => {
		return http.post('usuario/post',
			{
				nome: usuario.nome,
				email: usuario.email,
				senha: usuario.senha
			}
		)
	}
}