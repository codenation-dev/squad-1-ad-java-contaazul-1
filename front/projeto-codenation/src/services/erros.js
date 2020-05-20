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
	}

}