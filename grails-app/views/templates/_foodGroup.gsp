
<!-- FoodGroup Templates
================================================== -->

<script type="text/x-handlebars" data-template-name="foodGroups">

<div class="admin-section">
<section id="admin-items">
  <div class="page-header">
    <h1> Food Groups </h1>
  </div>
  
  <div class="row">

    <div class="span6">
      <table class="table table-bordered table-striped table-hover">
        <thead>
          <tr>
            <th>#</th>
            <th>nameKey</th>
            <th>name</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
     {{#each item in controller itemController="foodGroup" }}
          <tr>
            <td>{{ id }}</td>
            <td>
                {{#if isEditing}}
                  {{view Cook.EditFoodGroupView valueBinding="nameKey" }}
                {{else}}
                  <label {{action "editFoodGroup" on="doubleClick"}}>{{nameKey}}</label>
                {{/if}}
            </td>
            <td>{{ name }}</td>
            <td><button {{action "removeFoodGroup"}} class="destroy">delete</button></td>
          </tr>
     {{/each}}
        </tbody>
      </table>

    {{partial "foodGroupCreate" }}

    </div> <!-- /panel -->
    
    <div class="span4">
    </div>

  </div> <!-- /row -->
</section>
</div>
</script>

<script type="text/x-handlebars" data-template-name="_foodGroupCreate">
       <form class="well form-search">
          <div class="control-foodGroup">
            <label class="control-label" for="input01">enter new nameKey</label>
            <div class="controls">
                {{view Ember.TextField  valueBinding="controller.newNameKey" }}
                <button {{action "createFoodGroup" this}} class="create">add new food group</button>
              <p class="help-block">add manually to the i18n bundle</p>
            </div>
          </div>
        </form>
</script>

