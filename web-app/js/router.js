/*global Cook Ember*/
'use strict';

/*
Cook.CategoryRoute = Ember.Route.extend({
	model: function () {
		return Cook.Category.find();
	}
});

Cook.CategoryIndexRoute = Ember.Route.extend({
	setupController: function () {
		var categorys = Cook.Category.find();
		this.controllerFor('category').set('category', category);
	}
});
*/
Cook.Router.map(function() {
  this.resource('category', { path: '/category/:id' }, function() {
    this.route('edit');
  });
});