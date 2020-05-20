import { http } from './config'

export default {
	listar: () => {
		return http.get('usuario/get')
	},


	login: (username, senha) => {
		return http.post('api/auth/signin',
			{
				username: username,
				password: senha,
			}
		)
	},

	salvar: (usuario) => {
		return http.post('api/auth/signup',
			{
				name: usuario.nome,
				username: usuario.username,
				email: usuario.email,
				roles: ["user"],
				password: usuario.senha
			}
		)
	}
}