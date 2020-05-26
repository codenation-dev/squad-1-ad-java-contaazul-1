import { http } from './config'

export default {
	listar: () => {
		return http.get('usuario/')
	},


	login: (username, senha, token) => {
		headers: {
			Authorization: 'Bearer ' + token
		}
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
	},
	alterarSenha: (usuario) => {
		return http.get('usuario/alterarsenha/' + usuario.email + '/' + usuario.senhaAntiga + '/' + usuario.senhaNova1 + '/' + usuario.senhaNova2,
			{
				email: usuario.email,
				senhaAntiga: usuario.senhaAntiga,
				senhaNova1: usuario.senhaNova1,
				senhaNova2: usuario.senhaNova2
			})
	}
}
