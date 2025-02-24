import requests
from dotenv import load_dotenv
from geopy.geocoders import Nominatim
from flask import Flask, request, redirect, session, jsonify
import os

load_dotenv()
app = Flask(__name__)

personal_acc_token = os.getenv("GITHUB_PERSONAL_ACC_TOKEN")

url = "https://api.github.com/graphql"

headers = {
    "Authorization": f"Bearer {personal_acc_token}",
    "Content-Type": "application/json"
}

query = """ {
search(query: "language:python state:open label:\\"help wanted\\"", first: 20, type: ISSUE) {
edges {
  node {
  ... on Issue {
    title
    url
  }
}
}
}
}
"""
res = requests.post(url, json={"query": query}, headers=headers)
data = res.json()
print(data)


# batch fetches projects in increments of 15
def fetchProjects(): 
    
    return None
          