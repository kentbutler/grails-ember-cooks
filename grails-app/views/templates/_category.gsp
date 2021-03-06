

<!-- Category Templates
================================================== -->

<script type="text/x-handlebars" data-template-name="categorys">

<div class="admin-section">
<section id="admin-items">
  <div class="page-header">
    <h1>Categories</h1>
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
     {{#each item in controller itemController="category" }}
          <tr>
            <td>{{ id }}</td>
            <td>
                {{#if isEditing}}
                  {{view Cook.EditCategoryView valueBinding="nameKey" }}
                {{else}}
                  <label {{action "editCategory" on="doubleClick"}}>{{nameKey}}</label>
                {{/if}}
            </td>
            <td>{{ name }}</td>
            <td><button {{action "removeCategory"}} class="destroy">delete</button></td>
          </tr>
     {{/each}}
        </tbody>
      </table>

    {{partial "categoryCreate" }}

    </div> <!-- /panel -->
    
    <div class="span4">
    </div>

  </div> <!-- /row -->
</section>
</div>
</script>

<script type="text/x-handlebars" data-template-name="_categoryCreate-alternateFormUsingTable">
<form class="well form-search">
      <table class="table table-bordered table-striped table-hover">
        <thead>
          <tr>
            <th>nameKey</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
                  {{view Ember.TextField  valueBinding="controller.newNameKey" }}
            </td>
            <td><button {{action "createCategory" this}} class="create">add new</button></td>
          </tr>
        </tbody>
      </table>
</form>
</script>

<script type="text/x-handlebars" data-template-name="_categoryCreate">
       <form class="well form-search">
          <div class="control-group">
            <label class="control-label" for="input01">enter new nameKey</label>
            <div class="controls">
                {{view Ember.TextField  valueBinding="controller.newNameKey" }}
                <button {{action "createCategory" this}} class="create">add new category</button>
              <p class="help-block">add manually to the i18n bundle</p>
            </div>
          </div>
        </form>
</script>


