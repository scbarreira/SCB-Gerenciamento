<%-- 
    Document   : movi
    Created on : 7 de out. de 2021, 03:33:14
    Author     : silvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@page import="SCBGerenciamento.Container" %>
<%@page import="SCBGerenciamento.Movi" %>
<%@include file="WEB-INF/jspf/header.jspf"%>
<%@include file="WEB-INF/jspf/footer.jspf"%>


<%
    if(request.getParameter("container") != null){
        String container = request.getParameter("container");
        int tipo_movi = Integer.parseInt(request.getParameter("tipo_movi"));
        String data_inicio = request.getParameter("data_inicio");
        String data_fim = request.getParameter("data_fim");
        
        Movi.inseir(container, tipo_movi, data_inicio, data_fim);
        
        if(request.getParameter("deletar_movement") != null){
            Movi.deletar(Long.parseLong(request.getParameter("deletar_movement")));
        }
    }
    
%>


<<main>
    <h2> Listagem </h2>
    
    <table class="ui celleb table">
        <thead>
            <th>Cliente</th>
            <th>Tipo</th>
            <th>Data Início</th>
            <th>Data Fim</th>
            <th>Deletar</th>
        </thead>
        
        <tbody>
            <% for(Movi m : Movi.listar()){%>
            <tr>
                <td><%= m.getContainer()%></td>
                <td><%= Movi.moviments(m.getTipo_movi()) %></td>
                        <td><%= m.getData_inicio()%></td>
                        <td><%= m.getData_fim() %></td>
                        <td>
                            <form method ="POST">
                                <input type="hidden" value="<%= m.getRowid()%>" name="deletar_movement"/>
                                <button class="ui button negative" type="submit">Excluir</button>
                            </form> 
                        </td>
            </tr>
            <%}%>
        </tbody> 
    </table>

        
         <h2> Cadastro </h2>
         
         <form class="ui form" method="POST">
            <div class="ui field">
                <label for="container"> Container</label>
                <select name="container" id="container"/>
                <%for (Container c : Container.listar()){%>
                    <option value="<%= c.getContainer()%>"><%= c.getContainer()%></option>
               <%}%>             
             </select>
            </div>
             
            <div class="ui field">
                <label for="tipo_movi"> Tipo</label>
                <select name="tipo_movi" id="tipo_movi"/>
                    <option value="0"> Embarque </option>
                    <option value="1"> Descarga </option>
                    <option value="2"> Gate In </option>
                    <option value="3"> Gate Out </option>
                    <option value="4"> Reposicionamento </option>
                    <option value="5"> Pesagem </option>
                    <option value="6"> Scanner </option>
             </select>
            </div> 
             
             
            <div class="ui field">
                <label for="data_inicio"> Nome do cliente</label>
                <input type="date" name="data_inicio" id="data_inicio" required/>
            </div>
             
            <div class="ui field">
                <label for="data_fim"> Nome do cliente</label>
                <input type="date" name="data_fim" id="data_fim" required/>
            </div>
             
            <button class="ui button ptimary" type="submit"> Gravar </button>
        </form>    
</main>