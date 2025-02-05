import requests
from dotenv import load_dotenv
from geopy.geocoders import Nominatim
from flask import Flask, request, redirect, session, jsonify
import os



load_dotenv()
app = Flask(__name__)

places_key = os.getenv("RESTAURANT_KEY")
places_url = f'https://places.googleapis.com/v1/places:searchNearby'
geocoder = Nominatim(user_agent="swipebyte")

@app.route("/home/changelocation")
@app.route("/home")
def findCoordinates(location):
    full_loc = geocoder.geocode(location)
    lat = full_loc.latitude
    long = full_loc.longitude

    return {
        "Latitude": lat,
        "Longitude": long 
    }

def photo(photo_uri):
    link = f'https://places.googleapis.com/v1/{photo_uri}/media?maxHeightPx=800&maxWidthPx=800&skipHttpRedirect=true&key={places_key}'
    r = requests.get(link)
    r.raise_for_status()
    data = r.json()
    return data.get('photo_uri')

@app.route("/home")
@app.route("home/changelocation")
@app.route("home/newrecs")
def getNearby(location, preferences):
    body = {
  "includedTypes": preferences,
  "maxResultCount": 20,
  "locationRestriction": {
    "circle": {
      "center": {
        "latitude": findCoordinates(location).get("Latitude"),
        "longitude": findCoordinates(location).get("Longitude")
      },
      "radius": 20000.0
    }
  },
  "rankPreference": "POPULARITY"
}

    response = requests.post(places_url, headers={
        'Content-Type': 'application/json',
        'X-Goog-FieldMask': 'places.displayName,places.formattedAddress,places.types,places.websiteUri,places.rating,places.photos',
        'X-Goog-Api-Key': places_key
    }, json=body)

    response.raise_for_status()
    data = response.json()
    return data
          