"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";
import dynamic from "next/dynamic";

const SearchDropdown = dynamic(() => import("../components/SearchDropdown"), { ssr: false });


export default function RegisterPage() {
    const [formData, setFormData] = useState({
        username: "",
        first_name: "",
        last_name: "",
        password: "",
        confirmPassword: "",
        location: "",
        email: "",
    });

    const [locationSuggestions, setSuggestions] = useState([]);
    const [message, setMessage] = useState("");
    const router = useRouter();


    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleLocationChange = async (value) => {
        try {
            const locResponse = await fetch(
                `http://localhost:8080/location/autocomplete?input=${value}`,
                {
                    method: "GET",
                }
            );

            const locData = await locResponse.json();
            const locations = locData.predictions.map((loc) => ({
                value: loc.description,
                label: loc.description,
            }));

            setSuggestions(locations);
        } catch (error) {
            setMessage("No Locations found.");
        }
    };

    const handleLocationSelect = (selectedOption) => {
        console.log(selectedOption)
        setFormData((prev) => ({ ...prev, location: selectedOption.value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch("http://localhost:8080/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                const data = await response.text();
                setMessage(data);
                router.push('/login')
            } else {
                const errorText = await response.text();
                setMessage(errorText);
            }
        } catch (error) {
            setMessage("An error occurred. Please try again.");
        }
    };

    return (
        <div>
            <h1>Register</h1>
            <form onSubmit={handleSubmit} style={{ color: "black" }}>
                <input
                    type="text"
                    name="username"
                    placeholder="Username"
                    value={formData.username}
                    onChange={handleChange}
                    required
                />
                <SearchDropdown
                    suggestions={locationSuggestions}
                    onInputChange={handleLocationChange}
                    onSelect={handleLocationSelect}
                />
                <input
                    type="text"
                    name="first_name"
                    placeholder="First Name"
                    value={formData.first_name}
                    onChange={handleChange}
                    required
                />
                <input
                    type="text"
                    name="last_name"
                    placeholder="Last Name"
                    value={formData.last_name}
                    onChange={handleChange}
                    required
                />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                />
                <input
                    type="password"
                    name="confirmPassword"
                    placeholder="Confirm Password"
                    value={formData.confirmPassword}
                    onChange={handleChange}
                    required
                />
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                />
                <button
                    type="submit"
                    style={{ color: "white" }}
                    onClick={handleSubmit}
                >
                    Register
                </button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
}
