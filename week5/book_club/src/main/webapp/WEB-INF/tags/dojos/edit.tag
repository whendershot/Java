<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
<div class="container">
    <div class="row">
        <div class="col col-sm-12">
            <h1 class="">Edit a Dojo:</h1>
            <div class="card">
                <form:form method="post" action="/dojos/${dojo.id}" class="card-body" modelAttribute="dojo">
                    <input type = "hidden" name="_method" value="put">
                    <div class="row mb-3">
                        <form:label path="name" for="dojo_name" class="form-label">Name:</form:label>
                        <form:input path="name" type="text" class="form-control" name="dojo_name"/>
                        <form:errors path="name"/>
                    </div>
                    <div class="row justify-content-end">
                        <button type="submit" class="col-sm-3 btn btn-primary">Submit</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>