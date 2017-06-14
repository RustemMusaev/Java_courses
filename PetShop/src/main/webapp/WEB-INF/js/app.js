Ext.onReady(function () {

    var store = new Ext.data.JsonStore({

        autoDestroy: true,
        url: '/tickets',
        autoLoad: true,
        fields: [
            {name:'id',type:'integer'},
            {name:'email',type:'string'},
            {name:'phone',type:'string'},
            {name:'status',type:'boolean'}
            ]
    });

    var grid = new Ext.grid.GridPanel({
        title: 'Database',
        width:600,
        height: 600,
        store: store,
        columns: [
            {
                text: "Id",
                header: "ID",
                dataIndex: 'id',
                sortable: true
            },
            {
                text: "Email",
                header: "Email",
                dataIndex: 'email',
                sortable: true
            },
            {
                text: "Phone",
                header: "Phone",
                dataIndex: 'phone',
                sortable: true
            },
            {
                text: "Status",
                header: "Status",
                dataIndex: "status",
                sortable: true
            }
        ]
    });
    grid.render('grid1');
});