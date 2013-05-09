/*global Todos DS*/
'use strict';

Cook.Store = DS.Store.extend({
	revision: 12,
    adapter: DS.RESTAdapter.create({ 
    	bulkCommit: false, 
    	namespace: "cook/api",
    	serializer: Cook.RESTAdapter
    })
});

