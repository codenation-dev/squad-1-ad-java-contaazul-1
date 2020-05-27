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
	arquivarErro: (errosId, token) => {
		return http.put('erro/arquivarErro/' + errosId, {
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
	},
	deletarErro: (errosId, token) => {
		return http.delete('erro/deleteErros/' + errosId, {
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
	},
	detalhesErro: (idErro, token) => {

		return http.get('erro/' + idErro, {
			headers: {
				Authorization: 'Bearer ' + token
			}
		})
	}

}
