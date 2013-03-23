/*global Todos Ember*/
'use strict';

Cook.EditCategoryView = Ember.TextField.extend({
	classNames: ['edit'],

	valueBinding: 'category.nameKey',

	change: function () {
		var value = this.get('value');

		if (Ember.isEmpty(value)) {
			this.get('controller').removeCategory();
		}
	},

	focusOut: function () {
		this.set('controller.isEditing', false);
	},

	insertNewline: function () {
		this.set('controller.isEditing', false);
	},

	didInsertElement: function () {
		this.$().focus();
	}
});
