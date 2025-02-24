import requests
from dotenv import load_dotenv
from geopy.geocoders import Nominatim
from flask import Flask, request, redirect, session, jsonify
import os

load_dotenv()
app = Flask(__name__)

personal_acc_token = os.getenv("GITHUB_PERSONAL_ACC_TOKEN")

graphql_url = "https://api.github.com/graphql"
# Your GitHub personal access token (required for authentication)
headers = {
    "Authorization": f"Bearer {personal_acc_token}",
    "Content-Type": "application/json"
}

query = ''
# batch fetches projects in increments of 15
def fetchProjects(): 
    
    return None
          