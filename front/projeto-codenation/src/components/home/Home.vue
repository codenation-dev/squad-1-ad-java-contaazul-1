<template>
  <div>
    <label>Bem vindo {{usuario.nome}}.</label>
    <label>Seu token é {{usuario.token}}</label>
    <div class="margin">
      <select v-model="ambienteSelecionado">
        <option
          v-for="ambiente in filtroAmbiente"
          :value="ambiente.ambiente"
        >{{ ambiente.descricao }}</option>
      </select>
      <select>
        <option value="ordenarPor">Ordenar Por</option>
        <option value="nivel" selected>Nivel</option>
        <option value="frequencia">Frequência</option>
      </select>
      <select v-model="buscaSelecionado">
        <option v-for="buscar in filtroBuscar" :value="buscar.busca">{{ buscar.descricao }}</option>
      </select>
      <input type="search" @input="filtro = $event.target.value" placeholder="Buscar" />
    </div>
    <div>
      <button class="arquivar" @click="arquivar">Arquivar</button>
      <button class="deletar" @click="deletar">Apagar</button>
    </div>
    <div>
      <table>
        <thead>
          <tr>
            <th></th>
            <th>Nivel</th>
            <th>Log</th>
            <th>Eventos</th>
          </tr>
        </thead>
        <tbody>
          <template v-for="erro in descricaoErroFiltro">
            <tr v-if="erro.ambiente == ambienteSelecionado || ambienteSelecionado == 'todos'">
              <td>
                <input type="checkbox" v-model="erro.checked" />
              </td>
              <td>{{ erro.nivel }}</td>
              <td>{{ erro.log }}</td>
              <td>{{ erro.eventos }}</td>
            </tr>
          </template>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      usuario: {
        nome: "Larissa",
        token: "4d5748sdasd"
      },
      ambienteSelecionado: "",
      buscaSelecionado: "",

      filtroAmbiente: [
        { ambiente: "todos", descricao: "Todos" },
        { ambiente: "homologacao", descricao: "Homologação" },
        { ambiente: "desenvolvimento", descricao: "Desenvolvimento" },
        { ambiente: "producao", descricao: "Produção" }
      ],

      filtroBuscar: [
        { busca: "todos", descricao: "Todos" },
        { busca: "nivel", descricao: "Nível" },
        { busca: "descricao", descricao: "Descrição" },
        { busca: "origem", descricao: "Origem" }
      ],
      lista: [],
      listaSelecionados: [],
      filtro: ""
    };
  },
  mounted() {
    this.lista.push({
      id: 1,
      nivel: "nivel 01",
      log: "log erro 1",
      eventos: "evento a",
      checked: false,
      ambiente: "homologacao",
      frequencia: 2
    });
    this.lista.push({
      id: 2,
      nivel: "nivel 02",
      log: "log erro 2",
      eventos: "evento b",
      checked: false,
      ambiente: "desenvolvimento",
      frequencia: 5
    });
    this.lista.push({
      id: 3,
      nivel: "nivel 03",
      log: "log erro 3",
      eventos: "evento c",
      checked: false,
      ambiente: "producao",
      frequencia: 1
    });
  },
  computed: {
    descricaoErroFiltro() {
      if (this.filtro) {
        var exp = new RegExp(this.filtro.trim(), "i");
        if (this.buscaSelecionado == "nivel") {
          return this.lista.filter(erro => exp.test(erro.nivel));
        }
        if (this.buscaSelecionado == "descricao") {
          return this.lista.filter(erro => exp.test(erro.log));
        }
        if (this.buscaSelecionado == "origem") {
          return this.lista.filter(erro => exp.test(erro.eventos));
        }
      } else {
        return this.lista;
      }
    }
  },
  methods: {
    arquivar() {
      this.lista.forEach(erro => {
        if (erro.checked) {
          this.listaSelecionados.push(erro);
        }
      });

      //chamar rota arquivar enviando a lista selecionada

      if (this.listaSelecionados.length > 0) {
        alert(this.listaSelecionados.length + " erros selecionados");
      } else {
        alert("nenhum registro selecionado");
      }

      this.listaSelecionados = [];
    },
    deletar() {
      this.lista.forEach(erro => {
        if (erro.checked) {
          this.listaSelecionados.push(erro);
        }
      });

      //chamar rota deletar enviando a lista selecionada

      if (this.listaSelecionados.length > 0) {
        alert(this.listaSelecionados.length + " erros selecionados");
      } else {
        alert("nenhum registro selecionado");
      }

      this.listaSelecionados = [];
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
.arquivar {
  background-color: #4caf50;
}
.deletar {
  background-color: #f44336;
}
.margin {
  margin-top: 20px;
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
</style>