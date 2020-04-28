import {http} from './config'

export default {
	listar:() => {
		return http.get('usuario/get')
	},

	salvar:(usuario) => {
		return http.post('usuario/post',
			{nome: usuario.nome,
        	email: usuario.email,
        	senha: usuario.senha,
        	cpf: usuario.cpf,
        	token: usuario.token}
			)
	}		
}