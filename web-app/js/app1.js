/*global Backbone app*/
var app = app || {};
var ENTER_KEY = 13;


$(function(){
   
   'use strict';
   
   app.TEMPLATE_SUFFIX = '-template';
   app.INDEX = 'index';
   app.UNITS = 'units';

   /*
    * Define Header =====================================================
    */

   app.headerModel = new Backbone.Model({
      username : 'Steve'
   });
   /*
   app.HeaderViewModel = function(model) {
      this.username = kb.observable(model, 'username');
   };
   app.headerViewModel = new app.HeaderViewModel(app.headerModel);
   */
   app.headerViewModel = kb.viewModel(app.headerModel);
   ko.applyBindings(app.headerViewModel, $('#header')[0]);

   /*
    * Define App =====================================================
    */

   app.Unit = Backbone.Model.extend({
      url: "api/units"
  });
   
   /*
   app.Unit = function(id,name,nameKey,abbr,abbrKey) {
      this.id = ko.observable(id);
      this.name = ko.observable(name);
      this.nameKey = ko.observable(nameKey);
      this.abbr = ko.observable(abbr);
      this.abbrKey = ko.observable(abbrKey);
   }
   app.unitCollection = new Backbone.Collection([
       new app.Unit({id : "1", nameKey:"key.1", name:"Name 1", abbrKey:"abbr.1", abbr:"n1"}),
       new app.Unit({id : "2", nameKey:"key.2", name:"Name 2", abbrKey:"abbr.2", abbr:"n2"})
   ]);
   */
   app.UnitCollection = Backbone.Collection.extend({
      model: app.Unit,
      url: "api/units"
   });
   app.unitCollection = new app.UnitCollection();
   
   /*
   app.UnitViewModel = function(model) {
      var self = this;
      
      self.id = kb.observable(model, 'id');
      self.name = kb.observable(model, 'name');
      self.nameKey = kb.observable(model, 'nameKey');
      self.abbr = kb.observable(model, 'abbr');
      self.abbrKey = kb.observable(model, 'abbrKey');
      
      return this;
   };
    */
   
   app.UnitViewModel = kb.ViewModel.extend({
      constructor: function(model) {
         kb.ViewModel.prototype.constructor.apply(this, arguments);
      }
   });
   app.UnitsViewModel = function() {
      var self = this;

      self.selectedUnit = ko.observable();
            
      self.units = kb.collectionObservable(app.unitCollection, { view_model: app.UnitViewModel });
      
      self.init = function() {
         app.unitCollection.fetch();
      }
   }; 
   app.unitsViewModel = new app.UnitsViewModel();
   
   
   /*
   app.appModel = new Backbone.Model({
      panelName : 'index-template'
   });

   // VERSION 1: from kb Tutorial, last suggested way to build a ViewModel
   app.AppViewModel = kb.ViewModel.extend({
      read_only: false,
      self: this,
      constructor : function(model) {
         kb.ViewModel.prototype.constructor.apply(this, arguments);
         //this.panelName = kb.observable(model, 'panelName');
         
         this.currentPanel = ko.computed((function() {
            return this.panelName();
         }), this);
      }
   });
   app.appViewModel = new app.AppViewModel(app.appModel);
   */
   
   /*
   // Version 2: Another example from....
   app.appViewModel = kb.viewModel(app.appModel);
   app.appViewModel.currentPanel = ko.computed((function() {
      return this.panelName();
   }), app.appViewModel);
   */
   
   app.IndexViewModel = function(){
      var self = this;
      self.username = ko.observable('Steve');
   }
   app.indexViewModel = new app.IndexViewModel();
   
   
   // Just a concept about a ViewMap hash entry
   app.ViewModelMapEntry = function(name, viewModel) {
      var self = this;
      self.name = name;
      self.templateName = name + '-template';
      self.data = viewModel;
   }
  
  
   // Main ViewManager ViewModel
   app.AppViewModel = function(model){
      var self = this;
      
      self.viewModelMap = {
            "index" : new app.ViewModelMapEntry(app.INDEX, app.indexViewModel),
            "units" : new app.ViewModelMapEntry(app.UNITS, app.unitsViewModel)
      };
      
      self.currentPanel = ko.observable(self.viewModelMap[app.INDEX]);
      
      self.setView = function(viewName) {
           var nextView = self.viewModelMap[viewName];
           if (nextView.data && nextView.data.init) {
              nextView.data.init();
           }
           self.currentPanel(self.viewModelMap[viewName]);
           self.currentPanel.valueHasMutated();
           //TODO delete the data portion of the previous model
      };
      
   }
   app.appViewModel = new app.AppViewModel(app.appModel);
   ko.applyBindings(app.appViewModel, $('#content')[0]);
   
   
   /*
    * Define Router =====================================================
    */

   app.Router = Backbone.Router.extend({

      routes : {
         "#" : "home",
         "home" : "home",
         "index.html" : "home",
         "units" : "units"
      },

      // extend does not add this to our prototype - call super
      route : function() {
         Backbone.Router.prototype.route.apply(this, arguments);
      },

      home : function() {
         app.appViewModel.setView(app.INDEX);
      },

      start : function() {
         Backbone.history.start();
      },
      
      units : function() {
         app.appViewModel.setView(app.UNITS);
      }

   // This is another way of rendering a template - FYI
   // var tpl = $("#index-template").html();
   // $("#target").html(_.template(template,{items:items}));
   });
   app.router = new app.Router;

   /*
   // Define additional routes
   app.router.route('units', null, function() {
      app.appModel.set({'panelName':'units-template'});
      app.unitViewModel = new app.UnitViewModel();
      ko.applyBindings(app.unitViewModel, $('#unitsContent')[0]);
    });
   */

   app.router.start();

});
