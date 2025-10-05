import React, { useState, useEffect, useRef } from "react";
function App(){
    const [todos, setTodos] = useState(() => {
        // lấy dữ liệu từ localStorage khi load lần đầu
        const saved = localStorage.getItem("todos");
        return saved ? JSON.parse(saved) : [];
    });
    const [task, setTask]=useState("");
    const inputRef=useRef(null);
    useEffect(()=>{
        localStorage.setItem("todos",JSON.stringify(todos));
    },[todos]);
    const addTodo=()=>{
        if(task.trim()==="") return;
        setTodos([...todos,task]);
        setTask("");
        inputRef.current.focus();
    };
    const removeTodo=(index)=>{
        setTodos(todos.filter((_, i) => i !== index));
    };
    return(
        <div style={{ padding: "20px" }}>
            <h2>📌 Todo App (React Hooks)</h2>
            <input
                ref={inputRef}
                type="text"
                value={task}
                onChange={(e) => setTask(e.target.value)}
                placeholder="Nhập công việc..."
            />
            <button onClick={addTodo}>Thêm</button>

            <ul>
                {todos.map((t, i) => (
                    <li key={i}>
                        {t} <button onClick={() => removeTodo(i)}>❌ Xóa</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}
export default App