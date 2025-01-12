"use client";
import { useState, React } from "react";
import { useRouter } from "next/navigation";

export default function SetupProfile() {

    const cuisines = {
        'afghani': 'Afghani', 'african': 'African', 'newamerican': 'New American', 'tradamerican': 'Traditional American', "arabian": 'Arabian', "argentine": 'Argentine',
        "armenian": 'Armenian', "australian": 'Australian', "bangladeshi": 'Bangladeshi', "bbq": 'BBQ', "brazilian": 'Brazilian', "breakfast_brunch": "Breakfast/Brunch", 
        "british": 'British', "buffets": 'Buffets', "burgers": 'Burgers', "cafes": 'Cafes', "cajun": 'Cajun', "caribbean": 'Caribbean', "chicken_wings": 'Chicken Wings',
        "chinese": 'Chinese', "comfortfood": "Comfort Food", "cuban": "Cuban", "delis": "Delis", "diners": "Diners", "ethiopian": "Ethiopian", "filipino": "Filipino",
        "french": "French", "german": "German", "gluten_free": "Gluten-Free", "greek": "Greek", "halal": "Halal", "hotpot": "Hot Pot", "indpak": "Indian/Pakistani",
        "italian": "Italian", "japanese": "Japanese", "ramen": "Ramen", "korean": "Korean", "kosher": "Kosher", "mexican": "Mexican", "pizza": "Pizza", "russian": "Russian",
        "salad": "Salad", "sandwiches": "Sandwiches", "seafood": "Seafood", "soulfood": "Soul Food", "soup": "Soup", "southern": "Southern", "spanish": "Spanish",
        "steak": "Steak", "sushi": "Sushi", "tapas": "Tapas", "thai": "Thai", "turkish": "Turkish", "vegan": "Vegan", "vegetarian": "Vegetarian", "vietnamese": "Vietnamese"
    }

    const userId = localStorage.getItem("userId");
    const bio = localStorage.getItem('bio');

    const [checkedCuisines, setCheckedCuisines] = useState([]);

    const [formData, setFormData] = useState({
        'bio': '',
        'favoriteCuisines': []
    }
    )

    const requestData = {
        ...formData,
        userId: localStorage.getItem("userId")
    };

    const router = useRouter();
    const handleRedirect = () => {
        router.push('/home');
    }

    const handleBioChange = (e) => {
        const {name, value} = e.target;
        setFormData((prev) => ({
            ...prev, [name]: value
        }));
    }
    
    const handleCuisineChange = (e) => {
        const {name, value} = e.target;

        setCheckedCuisines((prev) => {

            const updatedCuisines = e.target.checked ? 
            [...prev, value] :
            prev.filter((cuisine) => cuisine !== value);

            setFormData((formData) => ({
                ...formData,
                favoriteCuisines: updatedCuisines,
                }));
            return updatedCuisines;
    });
};


    const [message, setMessage] = useState('');
    const handleSubmit = async (e) => {

        e.preventDefault();
        console.log('form data:', formData);
        try {
            const response = await fetch('http://localhost:8080/profile', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestData),
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
                <form onSubmit={handleSubmit} style={{ color: "black" }}>
                    <input
                        style={{ color: "black" }}
                        type="text"
                        name="bio"
                        placeholder="Bio"
                        value={formData.bio}
                        onChange={handleBioChange}
                    />
                    {Object.entries(cuisines).map(([key, value]) => (
                    <div key={key}>
                        <label style={{color: 'white'}}>
                            <input
                                type="checkbox"
                                value={key}
                                onChange={handleCuisineChange}
                            />
                            {value}
                        </label>
                    </div>
                ))}
                    <button type="submit" style={{ color: "white" }}>
                        Submit
                    </button>
                </form>
            </div>
        );
}