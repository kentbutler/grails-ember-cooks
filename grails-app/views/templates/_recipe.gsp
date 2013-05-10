
<!-- RawIngredient Templates
================================================== -->

<script type="text/x-handlebars" data-template-name="recipes/new">

<div class="admin-section">
<section id="admin-items">
  <div class="page-header">
    <h1> Create Your Recipe </h1>
  </div>

  <div class="row">
    <div class="span7">
          {{#if stageBase}}
             {{partial "recipeBase" }}
          {{/if}}
          {{#if stageIngredients}}
             {{partial "recipeIngredients" }}
          {{/if}}
          {{#if stageSteps}}
             {{partial "recipeSteps" }}
          {{/if}}
        <div class="control-group">
            {{#if hasPrevStage}}
            <button {{action "prevStage"}} class="button">previous</button>
            {{/if}}
            {{#if hasNextStage}}
            <button {{action "nextStage"}} class="button">next</button>
            {{/if}}
            {{#if isLastStage}}
            <button {{action "save"}} class="button">save recipe</button>
            {{/if}}
        </div>
    </div>

    <div class="span4">
       {{partial "viewRecipe" }}
    </div>

  </div> <!-- /row -->
</section>
</div>
</script>

<script type="text/x-handlebars" data-template-name="_viewRecipe">
    <div>
        <h3>{{name}}</h3>
        <p>{{description}}</p>
       {{#if ingredients}}
          <h4>Ingredients:</h4>
          <ul>
          {{#each ingredient in ingredients }}
             {{#with ingredient}}
             <li>{{quantity}}{{unit.abbr}} {{rawIngredient.name}}  {{#if notes}}({{notes}}){{/if}}</li>
             {{/with}}
          {{/each}}
          </ol>
       {{/if}}
       {{#if steps}}
          <h4>Steps:</h4>
          <ol>
          {{#each step in steps }}
             <li>{{step.description}}</li>
          {{/each}}
          </ol>
       {{/if}}
    </div>
</script>

<script type="text/x-handlebars" data-template-name="_recipeBase">
   <legend>Enter basic information</legend>
    <div class="well">

       <label class="control-label" for="nameField">name</label>
       {{view Ember.TextField id="nameField" class="span4" valueBinding="name" }}

       <label class="control-label" for="descriptionField">description</label>
       {{view Ember.TextArea id="descriptionField" class="span5" valueBinding="description" rows="6" cols="180" }}

    </div>

</script>

<script type="text/x-handlebars" data-template-name="_recipeIngredients">
 <legend>Enter all required ingredients</legend>

 <div class="well">
      <table class="table table-bordered table-striped table-hover">
        <thead>
          <tr>
            <th>quantity</th>
            <th>unit</th>
            <th>ingredient</th>
            <th>notes</th>
          </tr>
        </thead>
        <tbody>
     {{#each ingredient in ingredients }}
          <tr>
            <td>
             {{view Ember.TextField class="span1" valueBinding="ingredient.quantity" }}
            </td>
            <td>
             {{view Ember.Select class="span1"
                    contentBinding="controllers.units.content"
                    selectionBinding="ingredient.unit"
                    optionValuePath="content.id"
                    optionLabelPath="content.abbr"
                    prompt="select" }}
            </td>
            <td>
            {{view Ember.Select class="span2"
                    contentBinding="controllers.rawIngredients.content"
                    selectionBinding="ingredient.rawIngredient"
                    optionValuePath="content.id"
                    optionLabelPath="content.name"
                    prompt="select" }}
            </td>
            <td>
             {{view Ember.TextField class="span3" valueBinding="ingredient.notes" }}
            </td>

          </tr>
     {{/each}}
        </tbody>
      </table>
      <button {{action "addIngredient"}} class="destroy">add ingredient</button>
  </div>
</script>

<script type="text/x-handlebars" data-template-name="_recipeSteps">
 <legend>Enter instructions</legend>

 <div class="well">
      <table class="table table-bordered table-striped table-hover">
        <thead>
          <tr>
            <th>number</th>
            <th>description</th>
          </tr>
        </thead>
        <tbody>
     {{#each step in steps }}
          <tr>
            <td>
             {{view Ember.TextField class="span1" valueBinding="step.number" }}
            </td>
            <td>
             {{view Ember.TextArea class="span4" valueBinding="step.description" rows="1" }}
            </td>
          </tr>
     {{/each}}
        </tbody>
      </table>
      <button {{action "addStep"}} class="destroy">add step</button>
  </div>
</script>
