
<!-- RawIngredient Templates
================================================== -->

<script type="text/x-handlebars" data-template-name="rawIngredients">

<div class="admin-section">
<section id="admin-items">
  <div class="page-header">
    <h1> Raw Ingredients </h1>
  </div>

  
  <div class="row">

    <div class="span6">

  <div class="well">
   <button {{action "saveChanges"}} class="button">save changes</button>
   <button {{action "undoChanges"}} class="button">undo changes</button>
  </div>

      <table class="table table-bordered table-striped table-hover">
        <thead>
          <tr>
            <th>#</th>
            <th>nameKey</th>
            <th>name</th>
            <th>food group</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
     {{#each item in controller itemController="rawIngredient" }}
          <tr>
            <td>{{ id }}</td>
            <td>
                {{#if isEditing}}
                  {{view Cook.EditRawIngredientView valueBinding="nameKey" }}
                {{else}}
                  <label {{action "editRawIngredient" on="doubleClick"}}>{{nameKey}}</label>
                {{/if}}
            </td>
            <td>{{ name }}</td>
            <td>
            {{view Ember.Select
                    contentBinding="controllers.foodGroups.content"
                    selectionBinding="foodGroup"
                    optionValuePath="content.id"
                    optionLabelPath="content.name"
                    prompt="Select Food Group" }}
            
            </td>
            <td><button {{action "removeRawIngredient"}} class="destroy">delete</button></td>
          </tr>
     {{/each}}
        </tbody>
      </table>

    {{partial "rawIngredientCreate" }}

    </div> <!-- /panel -->
    
    <div class="span4">
    </div>

  </div> <!-- /row -->
</section>
</div>
</script>

<script type="text/x-handlebars" data-template-name="_rawIngredientCreate">
       <form class="well form-search">
          <div class="control-rawIngredient">
            <label class="control-label" for="input01">enter new nameKey</label>
            <div class="controls">
                {{view Ember.TextField  valueBinding="controller.newNameKey" }}

                {{view Ember.Select
                    contentBinding="controllers.foodGroups.content"
                    selectionBinding="newFoodGroup"
                    optionValuePath="content.id"
                    optionLabelPath="content.name"
                    prompt="Select Food Group" }}

                <button {{action "createRawIngredient" this}} class="create">add new raw ingredient</button>
              <p class="help-block">add manually to the i18n bundle</p>
            </div>
          </div>
        </form>
</script>

