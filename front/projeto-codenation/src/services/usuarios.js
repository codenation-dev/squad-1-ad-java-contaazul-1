import { http } from './config'

export default {
	listar: () => {
		return http.get('usuario/get')
	},

	login: (nome, senha) => {
		return http.get('login/get/' + nome + '/' + senha)
	},

	salvar: (usuario) => {
		return http.post('api/auth/signup',
			{
				name: usuario.nome,
				username: usuario.username,
				email: usuario.email,
				role: ["user"],
				password: usuario.senha
			}
		)
	}
}