Ext.onReady(function() {

    var myData = [
        ['3m Co', 71.72, 0.02,  0.03,  '9/1 12:00am'],
        ['Alcoa Inc', 29.01, 0.42,  1.47,  '9/1 12:00am'],
        //..................
        ['Verizon Communications', 35.57, 0.39,  1.11,  '9/1 12:00am'],
        ['Wal-Mart Stores, Inc.', 45.45, 0.73,  1.63,  '9/1 12:00am']
    ];

    var store = Ext.create('Ext.data.ArrayStore', {
        fields: ['company','price','change','pctChange','lastChange'],
        data: myData
    });

    Ext.create('Ext.grid.Panel', {
        store: store,
        columns: [
            {
                text     : 'Company',
                dataIndex: 'company'
            },
            {
                text     : 'Price',
                dataIndex: 'price'
            },
            {
                text     : 'Change',
                dataIndex: 'change'
            },
            {
                text     : '% Change',
                dataIndex: 'pctChange'
            },
            {
                text     : 'Last Updated',
                dataIndex: 'lastChange'
            }
        ],
        height: 350,
        width: 600,
        title: 'Простейшая статическая таблица grid',
        renderTo: 'grid1'
    });
});