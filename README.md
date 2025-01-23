# Food Ordering App - LenDenClub Assignment

This is a native Android application developed using Kotlin to fulfill the requirements of LenDenClub's frontend assignment. The app allows users to browse restaurants, view menus, manage a cart, and place orders, demonstrating a user-friendly and scalable implementation with modern Android development practices.


## Features

1. **Home Screen (Restaurant List)** 

This Fetches a list of restaurants from the server API.
API Endpoint: https://lendenclub-backend.onrender.com/restaurants
Curl
```
curl --location 'https://lendenclub-backend.onrender.com/restaurants'
```
Response:
```json
[
    {
        "id": 1,
        "name": "The Gourmet Spot",
        "restaurantImage": "https://picsum.photos/id/102/4320/3240",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "ratings": "4.5"
    },
    {
        "id": 2,
        "name": "Spice Symphony",
        "restaurantImage": "https://picsum.photos/id/103/2592/1936",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "ratings": "4.0"
    },
    {
        "id": 3,
        "name": "Sushi Heaven",
        "restaurantImage": "https://picsum.photos/id/104/3840/2160",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "ratings": "4.8"
    },
    {
        "id": 4,
        "name": "The Taco Stand",
        "restaurantImage": "https://picsum.photos/id/106/2592/1728",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "ratings": "4.2"
    },
    {
        "id": 5,
        "name": "Pasta Paradise",
        "restaurantImage": "https://picsum.photos/id/107/5000/3333",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "ratings": "4.6"
    },
    {
        "id": 6,
        "name": "Burger King",
        "restaurantImage": "https://picsum.photos/id/108/2000/1333",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "ratings": "3.9"
    },
    {
        "id": 7,
        "name": "Royal Sushi",
        "restaurantImage": "https://picsum.photos/id/109/4287/2392",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "ratings": "4.7"
    },
    {
        "id": 8,
        "name": "Flavors of India",
        "restaurantImage": "https://picsum.photos/id/110/5000/3333",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "ratings": "4.3"
    },
    {
        "id": 9,
        "name": "The Pizza Place",
        "restaurantImage": "https://picsum.photos/id/111/4400/2656",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "ratings": "4.1"
    },
    {
        "id": 10,
        "name": "Sushi Express",
        "restaurantImage": "https://picsum.photos/id/112/4200/2800",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "ratings": "4.4"
    }
]
```


Implements a search bar to dynamically filter restaurants by name from SearchView. 

Caches restaurant data locally using Room Database for offline support and faster subsequent access.

Handles network errors gracefully by displaying cached data in case of server or connectivity issues.

2. **Restaurant Menu Screen**

Displays a restaurant’s menu upon selection.

API Endpoint: https://lendenclub-backend.onrender.com/restaurants/menu/{id}
(Replace {id} with the restaurant’s unique identifier.)

Curl ```curl --location 'https://lendenclub-backend.onrender.com/restaurants/menu/10'```
**Example Response**
```json
{
    "id": 10,
    "menu": [
        {
            "menu_id": 1001,
            "name": "Salmon Roll",
            "price": 900,
            "description": "Fresh salmon wrapped in sushi rice and seaweed.",
            "image": "https://picsum.photos/id/112/4200/2800"
        },
        {
            "menu_id": 1002,
            "name": "Ebi Nigiri",
            "price": 800,
            "description": "Shrimp nigiri with vinegared rice and a dash of wasabi.",
            "image": "https://picsum.photos/id/112/4200/2800"
        },
        {
            "menu_id": 1003,
            "name": "Dragon Roll",
            "price": 1002,
            "description": "Eel and avocado roll topped with thin slices of avocado.",
            "image": "https://picsum.photos/id/112/4200/2800"
        },
        {
            "menu_id": 1004,
            "name": "Tuna Sashimi",
            "price": 1001,
            "description": "Thinly sliced fresh tuna served with soy sauce and wasabi.",
            "image": "https://picsum.photos/id/112/4200/2800"
        },
        {
            "menu_id": 1005,
            "name": "Miso Soup",
            "price": 400,
            "description": "Traditional Japanese miso soup served with tofu and green onions.",
            "image": "https://picsum.photos/id/112/4200/2800"
        }
    ]
}
```
Users can: (View menu items with names of the dish, descriptions of the dish, prices, and images of the dish.
feat-Add menu items to the cart.
Caches menu data locally for enhanced performance and offline access.

**3. Cart Screen**

Displays selected cart items with: (Item names, quantities, and prices.)
Shows total price of all items in the cart.

Allows users to:
Increment or decrement cart item quantities.
Remove items from the cart.

**4. Checkout Screen**
Accepts user input for:(Name, Email, Address)

## Technical Highlights

1. Architecture: Implements MVVM architecture with repository pattern for clean, modular, and testable code.
2. Local Caching: Uses Room Database to store restaurants, menus, and cart items.
3. Error Handling: Provides user-friendly messages for network or server failures, ensuring a smooth user experience.
4. Design Guidelines: Adheres to Material Design principles for a polished and responsive UI.
5. APIs: Mock APIs created using Node.js and Express.


## Steps to Build and Run
1. Clone the repository:
```
git clone <repository_url>
```
Open the project in Android Studio.

## External Libraries Used
Room Database: For storing the data locally and for local caching.
Retrofit: For network requests.
Glide: For image loading and caching.
Coroutines: For background tasks.


## Known Limitations
No comprehensive unit tests included due to time constraints.


