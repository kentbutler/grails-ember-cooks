
<!-- Unit Templates
================================================== -->

<script type="text/x-handlebars" data-template-name="units">

<div class="admin-section">
<section id="admin-items">
  <div class="page-header">
    <h1>Units of Measure</h1>
  </div>
  
  <div class="row">

    <div class="span8">
      <table class="table table-bordered table-striped table-hover">
        <thead>
          <tr>
            <th>#</th>
            <th>nameKey</th>
            <th>name</th>
            <th>abbrKey</th>
            <th>abbr</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
     {{#each item in controller itemController="unit" }}
          <tr>
            <td>{{ id }}</td>
            <td>
                {{#if isEditing}}
                  {{view Cook.EditUnitView valueBinding="nameKey" }}
                {{else}}
                  <label {{action "editUnit" on="doubleClick"}}>{{nameKey}}</label>
                {{/if}}
            </td>
            <td>{{ name }}</td>
            <td>
                {{#if isEditing}}
                  {{view Cook.EditUnitAbbrView valueBinding="abbrKey" }}
                {{else}}
                  <label {{action "editUnit" on="doubleClick"}}>{{abbrKey}}</label>
                {{/if}}
            </td>
            <td>{{ name }}</td>
            <td><button {{action "removeUnit"}} class="destroy">delete</button></td>
          </tr>
     {{/each}}
        </tbody>
      </table>

    {{partial "unitCreate" }}

    </div> <!-- /panel -->
    
    <div class="span4">
    </div>

  </div> <!-- /row -->
</section>
</div>
</script>

<script type="text/x-handlebars" data-template-name="_unitCreate">
        <form class="well form-search">
                {{view Ember.TextField placeholder="nameKey" valueBinding="controller.newNameKey" }}
                {{view Ember.TextField placeholder="abbrKey" valueBinding="controller.newAbbrKey" }}
                <button {{action "createUnit" this}} class="create">add new unit</button>
              <p class="help-block">add manually to the i18n bundle</p>
        </form>
</script>

