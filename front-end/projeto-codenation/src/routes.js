import Cadastro from './components/cadastro/Cadastro.vue'
import Login from './components/login/Login.vue'
import AlterarSenha from './components/alterar-senha/AlterarSenha.vue'
import Home from './components/home/Home.vue'
import DetalheErro from './components/detalhe-erro/DetalheErro.vue'

export const routes = [
    { path: '', component: Login, titulo: 'Login' },
    { path: '/cadastro', component: Cadastro, titulo: 'Cadastro' },
    { path: '/alterar-senha', component: AlterarSenha, titulo: 'Alterar Senha' },
    { path: '/home', component: Home, titulo: 'Home' },
    { path: '/detalhe-erro', component: DetalheErro, titulo: 'Detalhe erro' }
]