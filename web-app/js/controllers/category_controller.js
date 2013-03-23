/*global Categorys Ember*/
'use strict';

Cook.CategoryController = Ember.ObjectController.extend({
	isEditing: false,

	editCategory: function () {
		this.set('isEditing', true);
	},

	removeCategory: function () {
		var category = this.get('model');

		category.deleteRecord();
		category.get('store').commit();
	}
});
