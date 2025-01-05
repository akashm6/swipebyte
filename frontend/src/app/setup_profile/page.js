"use client";
import { useState, React } from "react";
import { useRouter } from "next/navigation";

export default function SetupProfile() {

    const [formData, setFormData] = useState({
        'bio': '',
        'favoriteCuisines': ''
    }
    )

    const router = useRouter();
    const handleRedirect = () => {
        router.push('/');
    }

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData((prev) => ({
            ...prev, [name]: value
        }));
    }

    const [message, setMessage] = useState('');
    const handleSubmit = async (e) => {

        e.preventDefault();
        console.log('form data:', formData);
        try {
            const response = await fetch('http://localhost:8080/setup_profile', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData),
            })

            if (response.ok) {
                const success = await response.text();
                setMessage(success);
            }
            else {
                const errorText = await response.text();
                setMessage(errorText);
            }
        }
        catch (error) {
            setMessage("An error occurred. Please try again.");   

        };
        handleRedirect();
        };
        
    return (
        <div>
            <form onSubmit={handleSubmit} style = {{color: "black"}}>
                <input style={{color: "black"}}
                    type = 'text'
                    name = 'bio'
                    placeholder = 'bio'
                    value = {formData.bio}
                    onChange={handleChange}
                />
                <input style={{color: "black"}}
                    type = 'text'
                    name = 'favoriteCuisines'
                    placeholder = 'cuisines'
                    value = {formData.favoriteCuisines}
                    onChange={handleChange}
                />
                <button type="submit" style={{color: 'white'}} onClick={handleRedirect}>Submit</button>
            </form>
        </div>
    )
}