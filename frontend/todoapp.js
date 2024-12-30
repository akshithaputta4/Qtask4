document.addEventListener('DOMContentLoaded', () => {
    const todoInput = document.getElementById("todo-input");
    const addButton = document.getElementById("add-button");
    const todoList = document.getElementById("todo-list");
    const completedCount = document.getElementById("completed-count");
    const totalCount = document.getElementById("total-count");

    let cc = 0; 
    let tc = 0; 

    addButton.addEventListener('click', async () => { 
        window.alert('Add Button Clicked'); 
        const todoText = todoInput.value.trim();
        if (todoText !== '') {
            const response = await fetch('http://localhost:8090/api/tasks/addTask', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ text: todoText, completed: false })
            });
            const todo = await response.json();
            renderTodo(todo);
            tc++;
            updateTotalCount();
            todoInput.value = '';
        }
    });

    function renderTodo(todo) {
        const todoCard = document.createElement('div');
        todoCard.className = "todocard";
        todoCard.dataset.id = todo.id;

        const todoSpan = document.createElement('span');
        todoSpan.textContent = todo.text;
        todoSpan.className = "todoSpan";
        todoCard.appendChild(todoSpan);

        const completeButton = document.createElement('button');
        completeButton.textContent = 'yes';
        completeButton.className = "completebutton";
        todoCard.appendChild(completeButton);

        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'no';
        deleteButton.className = "deletebutton";
        todoCard.appendChild(deleteButton);

        todoList.append(todoCard);

        completeButton.addEventListener('click', async () => {
            window.alert('Yes button clicked'); 
            const isCompleted = !todoCard.classList.contains("completed");
            if (isCompleted) {
                todoCard.classList.add("completed");
                cc++;
            } else {
                todoCard.classList.remove("completed");
                cc--;
            }
            updateCompletedCount();

            const updateResponse = await fetch(`http://localhost:8090/api/tasks/updateTask/${todo.id}?completed=${isCompleted}`, {
                method: 'PUT',
            });

            if (updateResponse.ok) {
                const updatedTask = await updateResponse.json();
                window.alert('Task updated: ' + updatedTask.text); 
            } else {
                window.alert('Failed to update task');
            }
        });

        deleteButton.addEventListener('click', async () => {
            window.alert('No button clicked');
            const deleteResponse = await fetch(`http://localhost:8090/api/tasks/deleteTask/${todo.id}`, {
                method: 'DELETE'
            });

            if (deleteResponse.ok) {
                window.alert('Task deleted successfully');
                todoList.removeChild(todoCard);
                tc--;
                if (todoCard.classList.contains("completed")) {
                    cc--;
                }
                updateCompletedCount();
                updateTotalCount();
            } else {
                window.alert('Failed to delete task');
            }
        });
    }

    function updateCompletedCount() {
        completedCount.textContent = cc;
    }

    function updateTotalCount() {
        totalCount.textContent = tc;
    }
});
