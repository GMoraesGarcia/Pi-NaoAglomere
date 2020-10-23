<%-- 
    Document   : Form_Agendamento
    Created on : 19/10/2020, 23:05:20
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Agendamento</h1>
        <form>
            <div>
                <label>Nome: </label>
                <input type="text" name="nome">
            </div>
            <div>
                <label>E-mail: </label>
                <input type="email" name="email">
            </div>
            <div>
                <label>Telefone:  </label>
                <input type="text" name="telefone">
            </div>
            <div>
                <label>data </label>
                <input type="date" name="data">
            </div>
            <div>
                <label>Horário </label>
                <input type="time" name="horario">
            </div>
            
        </form>
    </body>
</html>
