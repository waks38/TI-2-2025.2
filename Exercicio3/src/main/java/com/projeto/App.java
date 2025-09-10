package com.projeto;

import com.projeto.dao.CarroDAO;
import com.projeto.model.Carro;
import spark.Spark;
import java.util.List;

import static spark.Spark.*;

public class App {
    private static CarroDAO carroDAO = new CarroDAO();

    public static void main(String[] args) {
        port(8080); // Define a porta

        // Rota principal (READ - Listar todos)
        get("/", (req, res) -> {
            List<Carro> carros = carroDAO.listarTodos();
            StringBuilder html = new StringBuilder();

            html.append("<html><head><title>Lista de Carros</title>");
            html.append("<style>body{font-family: Arial, sans-serif;} table{width: 100%; border-collapse: collapse;} th,td{border: 1px solid #ddd; padding: 8px; text-align: left;} th{background-color: #f2f2f2;} form{display: inline;}</style>");
            html.append("</head><body>");
            html.append("<h1>CRUD de Carros com Java Spark</h1>");

            // Formulário para adicionar novo carro (CREATE)
            html.append("<h2>Adicionar Novo Carro</h2>");
            html.append("<form action='/carros' method='post'>");
            html.append("Marca: <input type='text' name='marca' required> ");
            html.append("Modelo: <input type='text' name='modelo' required> ");
            html.append("Ano: <input type='number' name='ano' required> ");
            html.append("<button type='submit'>Adicionar</button>");
            html.append("</form><hr>");

            // Tabela com os carros
            html.append("<h2>Carros Cadastrados</h2>");
            html.append("<table><tr><th>ID</th><th>Marca</th><th>Modelo</th><th>Ano</th><th>Ações</th></tr>");
            for (Carro carro : carros) {
                html.append("<tr>");
                html.append("<td>").append(carro.getId()).append("</td>");
                html.append("<td>").append(carro.getMarca()).append("</td>");
                html.append("<td>").append(carro.getModelo()).append("</td>");
                html.append("<td>").append(carro.getAno()).append("</td>");
                html.append("<td>");
                // Botão para editar
                html.append("<a href='/carros/editar/").append(carro.getId()).append("'>Editar</a> ");
                // Formulário para deletar
                html.append("<form action='/carros/deletar/").append(carro.getId()).append("' method='post' onsubmit='return confirm(\"Tem certeza?\");'>");
                html.append("<button type='submit'>Deletar</button>");
                html.append("</form>");
                html.append("</td>");
                html.append("</tr>");
            }
            html.append("</table></body></html>");

            return html.toString();
        });

        // Rota para processar a adição de carro (CREATE)
        post("/carros", (req, res) -> {
            String marca = req.queryParams("marca");
            String modelo = req.queryParams("modelo");
            int ano = Integer.parseInt(req.queryParams("ano"));
            carroDAO.adicionarCarro(new Carro(marca, modelo, ano));
            res.redirect("/");
            return null;
        });

        // Rota para mostrar o formulário de edição (UPDATE - parte 1)
        get("/carros/editar/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Carro carro = carroDAO.buscarPorId(id);
            if (carro != null) {
                StringBuilder html = new StringBuilder();
                html.append("<html><head><title>Editar Carro</title></head><body>");
                html.append("<h1>Editar Carro</h1>");
                html.append("<form action='/carros/editar' method='post'>");
                html.append("<input type='hidden' name='id' value='").append(carro.getId()).append("'>");
                html.append("Marca: <input type='text' name='marca' value='").append(carro.getMarca()).append("' required><br>");
                html.append("Modelo: <input type='text' name='modelo' value='").append(carro.getModelo()).append("' required><br>");
                html.append("Ano: <input type='number' name='ano' value='").append(carro.getAno()).append("' required><br>");
                html.append("<button type='submit'>Atualizar</button>");
                html.append("</form>");
                html.append("<a href='/'>Cancelar</a>");
                html.append("</body></html>");
                return html.toString();
            } else {
                res.status(404);
                return "Carro não encontrado.";
            }
        });

        // Rota para processar a atualização (UPDATE - parte 2)
        post("/carros/editar", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("id"));
            String marca = req.queryParams("marca");
            String modelo = req.queryParams("modelo");
            int ano = Integer.parseInt(req.queryParams("ano"));
            carroDAO.atualizarCarro(new Carro(id, marca, modelo, ano));
            res.redirect("/");
            return null;
        });
        
        // Rota para deletar um carro (DELETE)
        post("/carros/deletar/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            carroDAO.deletarCarro(id);
            res.redirect("/");
            return null;
        });
    }
}