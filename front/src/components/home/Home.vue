<template>
  <div>
    <div>
      <div style="height: 140px" class="text-center">
        <img class="logo" src="src\components\imagens\logo-central.png " style="margin: 0 0 15px; " />
      </div>
      <div class="justify-content-between justify-content-lg-between row col-md-12 p-0 m-0">
        <div style="width: 90%">
          <label style="font-weight: 700">Olá, </label>
          <label style="font-style: italic">{{usuario.nome}}.</label>
          <p>
            <label style="font-weight: 700">
              Seu token é
              <label style="font-size: 11px">{{usuario.token}}</label>
            </label>
          </p>
        </div>
        <div class="align-content-around row pr-3">
          <button @click="logout" class="botaoLogout">
            <i class="gg-log-out corLogout"></i>
          </button>
        </div>
      </div>
    </div>
    <div class="justify-content-around mt-4 row">
      <select v-model="ambienteSelecionado" class="col-2">
        <option value>Buscar por</option>
        <option v-for="ambiente in ambientes" :value="ambiente.tipo">{{ ambiente.descricao }}</option>
      </select>
      <select v-model="ordenacaoSelecionada" class="col-2">
        <option value disabled>Ordenar Por</option>
        <option v-for="ordem in ordenacao" :value="ordem.tipo">{{ ordem.descricao}}</option>
      </select>
      <select v-model="buscaSelecionado" class="col-2">
        <option value disabled>Buscar Por</option>
        <option v-for="buscar in filtroBuscar" :value="buscar.busca">{{ buscar.descricao }}</option>
      </select>
      <input
        type="search"
        @input="filtro = $event.target.value"
        placeholder="Buscar por"
        class="col-5"
      />
    </div>
    <div>
      <button @click="Arquivar" class="btn btn-success">Arquivar</button>
      <button @click="Deletar" class="btn btn-danger">Apagar</button>
      <button @click="listaErros" class="btn btn-info">
        <i class="gg-sync"></i>
      </button>
    </div>
    <div>
      <table>
        <thead>
          <tr>
            <th></th>
            <th>Nivel</th>
            <th>Log</th>
            <th>Eventos</th>
            <th>Detalhe Erro</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="erro of descricaoErroFiltro"
            :key="erro.id"
            v-if="erro.ambiente == ambienteSelecionado || filtrarTodosAmbientes"
          >
            <td>
              <input type="checkbox" :value="erro.id" v-model="checkedErro" />
            </td>
            <td>{{erro.nivel}}</td>
            <td>
              {{erro.titulo}}
              <br />
              {{erro.detalhes}}
              <br />
              {{erro.origem}}
            </td>
            <td>{{erro.eventos}}</td>
            <td>
              <router-link
                :to="{ name: 'detalhe', 
                params: { id: erro.id, 
                usuario:{
                  nome: usuario.nome,
                  token: usuario.token
                }}}"
              >Detalhar</router-link>
            </td>
          </tr>
          <tr></tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Erro from "../../services/erros";
import Usuario from "../../services/usuarios";

export default {
  data() {
    return {
      checkedErro: [],
      usuario: {
        nome: "",
        token: ""
      },
      erros: [],
      eventosTeste: [],
      ambienteSelecionado: "",
      ordenacaoSelecionada: "",
      buscaSelecionado: "",
      filtro: "",
      ambientes: [
        { tipo: "homologacao", descricao: "Homologação" },
        { tipo: "desenvolvimento", descricao: "Desenvolvimento" },
        { tipo: "producao", descricao: "Produção" }
      ],
      ordenacao: [
        { tipo: "nivel", descricao: "Nível" },
        { tipo: "frequencia", descricao: "Frequência" }
      ],
      filtroBuscar: [
        { busca: "nivel", descricao: "Nível" },
        { busca: "descricao", descricao: "Descrição" },
        { busca: "origem", descricao: "Origem" }
      ]
    };
  },
  methods: {
    logout() {
      this.$router.push({
        name: "login"
      });
    },
    detalheErro() {
      this.$router.push({
        name: "login"
      });
    },
    Arquivar() {
      console.log("Erros assinalados:" + this.checkedErro);
      Erro.arquivarErro(this.checkedErro, this.usuario.token).then(resposta => {
        if (resposta) {
          swal(
            "Erro arquivado com sucesso!",
            "Clique no botão para continuar.",
            "success"
          );
        } else {
          swal(
            "Não foi possível arquivar erro!",
            "Tente mais tarde.",
            "success"
          );
        }
      });
    },
    Deletar() {
      swal({
        title: "Deseja realmente excluir?",
        text: "",
        icon: "warning",
        buttons: true,
        dangerMode: true
      }).then(willDelete => {
        if (willDelete) {
          Erro.deletarErro(this.checkedErro, this.usuario.token).then(
            resposta => {
              if (resposta) {
                this.listaErros();
                swal(
                  "Erro deletado com sucesso!",
                  "Clique no botão para continuar.",
                  "success"
                );
              } else {
                swal(
                  "Não foi possível deletar erro!",
                  "Tente mais tarde.",
                  "success"
                );
              }
            }
          );
        }
      });
    },
    listaErros() {
      Erro.listar(this.usuario.token).then(resErro => {
        let errors = resErro.data;
        let promises = [];

        errors.forEach(erro => {
          promises.push(
            Erro.eventos(erro.titulo, this.usuario.token).then(resposta => {
              erro.eventos = resposta.data;
            })
          );
        });

        Promise.all(promises).then(() => {
          this.erros = errors;
        });
      });
    }
  },
  mounted() {
    this.usuario.nome = this.$route.params.usuario.nome;
    this.usuario.token = this.$route.params.usuario.token;
    this.listaErros();
  },

  computed: {
    filtrarTodosAmbientes() {
      return (
        this.ambienteSelecionado != "homologacao" &&
        this.ambienteSelecionado != "desenvolvimento" &&
        this.ambienteSelecionado != "producao"
      );
    },
    listaOrdenada() {
      if (this.ordenacaoSelecionada == "nivel") {
        return this.erros.sort(function(a, b) {
          if (a.nivel > b.nivel) {
            return 1;
          }
          if (a.nivel < b.nivel) {
            return -1;
          }
          return 0;
        });
      } else if (this.ordenacaoSelecionada == "frequencia") {
        return this.erros.sort(function(a, b) {
          if (a.eventos > b.eventos) {
            return 1;
          }
          if (a.eventos < b.eventos) {
            return -1;
          }
          return 0;
        });
      } else {
        return this.erros;
      }
    },
    descricaoErroFiltro() {
      if (this.filtro) {
        var exp = new RegExp(this.filtro.trim(), "i");
        if (this.buscaSelecionado == "nivel") {
          return this.listaOrdenada.filter(erro => exp.test(erro.nivel));
        }
        if (this.buscaSelecionado == "descricao") {
          return this.listaOrdenada.filter(erro => exp.test(erro.detalhes));
        }
        if (this.buscaSelecionado == "origem") {
          return this.listaOrdenada.filter(erro => exp.test(erro.origem));
        }
      } else {
        return this.listaOrdenada;
      }
    }
  }
};
</script>


<style scoped>
table {
  margin-top: 10px;
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}
td,
th {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 8px;
}
tr:nth-child(even) {
  background-color: #dddddd;
}
table,
th {
  border: 1px solid black;
}
button {
  border-radius: 4px;
  font-size: 15px;
  padding: 10px 20px;
  text-align: center;
  color: white;
  margin-top: 10px;
}
select {
  font-size: 15px;
  padding: 8px 15px;
  text-align: center;
  color: black;
}
input {
  font-size: 15px;
  padding: 8px 15px;
  text-align: left;
  color: black;
  width: 65%;
}
label {
  font-size: 20px;
  margin-top: 10px;
}
li {
  list-style: none;
}
</style>
