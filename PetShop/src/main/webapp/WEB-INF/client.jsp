<%@ page import="ru.rustem.model.Ticket" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test Project</title>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <link rel="stylesheet" href="/ext-all.css" type="text/css" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css" rel="stylesheet" />
    <script src="<c:url value="/js/ext-all.js" />" defer></script>
    <script type="text/javascript">
        Ext.onReady(function() {
            Ext.define('Ticket',{
                extend : 'Ext.data.Model',
                fields: ['id','email','phone','status']
            });

             var store = Ext.create('Ext.data.Store', {
                 model : 'Ticket',
                 proxy: {
                     type: 'ajax',
                     url: '/tickets',
                     reader: {
                         type: 'json',
                         root: 'data'
                     }
                 },
                 autoLoad: true
            });

            var grid = Ext.create('Ext.grid.Panel', {
                store: store,
                columns: [
                    {
                        text     : 'Id',
                        dataIndex: 'id'
                    },
                    {
                        text     : 'Email',
                        dataIndex: 'email'
                    },
                    {
                        text     : 'Phone',
                        dataIndex: 'phone'
                    },
                    {
                        text     : 'Status',
                        dataIndex: 'status'
                    }
                ],
                height: 350,
                width: 600,
                title: 'Простейшая статическая таблица grid',
                renderTo: 'grid1'
            });
        });
    </script>
</head>
<body>
<p>Простая таблица grid и хранилище Ext.data.ArrayStore</p>
<div id="grid1"></div>
</body>
</html>