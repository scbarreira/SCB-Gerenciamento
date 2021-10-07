<%-- 
    Document   : cantainer
    Created on : 7 de out. de 2021, 03:05:14
    Author     : silvi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="SCBGerenciamento.Container" %>
<%@include file="WEB-INF/jspf/header.jspf"%>
<%@include file="WEB-INF/jspf/footer.jspf"%>


<%
    if(request.getParameter("container") != null){
        String container = request.getParameter("container");
        String cliente = request.getParameter("cliente");
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        int status = Integer.parseInt(request.getParameter("status"));
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        
        Container.inseir(container, cliente, tipo, status, categoria);
        
        if(request.getParameter("deletar_container") != null){
            Container.deletar(request.getParameter("deletar_container"));
        }
    }
    
%>



<<main>
    <h2> Listagem </h2>
    
    <table class="ui celleb table">
        <thead>
            <th>Cliente</th>
            <th>Container</th>
            <th>Tipo</th>
            <th>Status</th>
            <th>Categoria</th>
            <th>Deletar</th>
        </thead>
        
        <tbody>
            <% for(Container cont : Container.listar()){%>
            <tr>
                <td><%= cont.getCliente()%></td>
                <td><%= cont.getCliente() %></td>
                        <td><%= cont.getContainer() %></td>
                        <td><%= cont.getTipo() == 0 ? "20" : "40" %></td>
                        <td><%= cont.getStatus() == 0 ? "Vazio" : "Cheio" %></td>
                        <td><%= cont.getCategoria()  == 0 ? "Importação" : "Exportação" %></td>
                        <td>
                            <form method ="POST">
                                <input type="hidden" value="<%= cont.getContainer()%>" name="deletar_container"/>
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
                <label for="cliente"> Nome do cliente</label>
                <input type="text" name="cliente" id="cliente" required/>
            </div>
            
            <div class="ui field">
                <label for="container"> Número do Container</label>
                <input type="text" name="container" id="container" pattern= "[A-Z]{4} [0-9]{7}" required/>
            </div>
    
             <div class="ui field">
                <label for="tipo"> Tipo</label>
                <select name="tipo" id="tipo"/>
                    <option value="0"> 20 </option>
                    <option value="1"> 40 </option>
             </select>
            </div>
            
            <div class="ui field">
                <label for="status"> Tipo</label>
                <select name="status" id="status"/>
                    <option value="0"> Vazio </option>
                    <option value="1"> Cheio </option>
             </select>
            </div>
            
             <div class="ui field">
                <label for="categoria"> Tipo</label>
                <select name="categoria" id="categoria"/>
                    <option value="0"> Importação </option>
                    <option value="1"> Exportação </option>
             </select>
            </div>
    
            <button class="ui button ptimary" type="submit"> Gravar </button>
        </form>    
</main>