import { http } from './config'

export default {
	listar: (token) => {
		return http.get('erro/get/', {
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
	},
	eventos: (titulo, token) => {
		return http.get('erro/get/eventos/' + titulo, {
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
	},
	arquivarErro: (errosId) => {
		return http.put('erro/arquivarErro/' + errosId)
	},
	deletarErro: (errosId) => {
		return http.delete('erro/deleteErros/' + errosId)
	},
	detalhesErro: (idErro) => {
		return http.get('erro/get/' + idErro)
	}

}