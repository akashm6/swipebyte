"use client";
import React, { useState } from "react";
import { useRouter } from "next/navigation";

export default function LoginPage() {
    const [formData, setFormData] = useState({
        username: "",
        email: "",
        password: "",
    });
    const [message, setMessage] = useState("");
    const router = useRouter();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8080/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(formData),
            });
            console.log(response)
            const data = await response.json(); 
            if (response.ok) {
                setMessage(data.message || "Login successful!");
                console.log("User ID:", data.id); 
                router.push("/"); 
            } else {
                setMessage(data.message || "Invalid credentials.");
            }
        } catch (error) {
            console.error("Error:", error);
            setMessage("An error occurred. Please try again.");
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
    <input style = {{color: 'black'}}
        type="text"
        name="username" 
        placeholder="Username"
        value={formData.username}
        onChange={handleChange}
    />
    <input style = {{color: 'black'}}
        type="text"
        name="email"
        placeholder="Email"
        value={formData.email}
        onChange={handleChange}
    />
    <input style = {{color: 'black'}}
        type="password"
        name="password"
        placeholder="Password"
        value={formData.password}
        onChange={handleChange}
    />
    <button type="submit">Login</button>
</form>

            {message && <p>{message}</p>}
        </div>
    );
}
