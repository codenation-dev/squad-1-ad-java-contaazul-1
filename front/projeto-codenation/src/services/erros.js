import { http } from './config'

export default {
	listar: (token) => {
		return http.get('erro/', {
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
	},
	eventos: (titulo, token) => {
		return http.get('erro/eventos/' + titulo, {
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
	},
	arquivarErro: (errosId) => {
		return http.put('erro/arquivarErro/' + errosId)
	},
	deletarErro: (errosId) => {
		return http.delete('erro/deleteErros' + errosId)
	},
	detalhesErro: (idErro, token) => {

		return http.get('erro/' + idErro, {
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
	}

}
