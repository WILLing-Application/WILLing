# WILLing

Remember to work on branches for pull requests
1. git pull origin master
2. git checkout -b your-branch-name
3. code!
4. Follow the git work flow below to push up your branch

### OUR GIT WORK FLOW!

Before you push your branch, add/commit & do this:
1. git checkout master
2. git pull origin master
3. git checkout your-branch-name
4. git merge master
5. Type in your commit message, read all prompts - note any merge conflicts, save it.
6. Resolve any merge conflicts, edit merged files
7. Repeat the process if new pulls occurred after you merged
8. git push origin your-branch-name
9. On GitHub, do a pull request & add reviewers 

---

## GLOSSARY

Visitor: visits landing page, signup page.

User: a registered user, can login and view albums to which they've been invited by an owner.

Owner: a registered user, can create and manage an album of items, can invite users to register, can allow users access to view their albums.

Feature: (curriculum) a component of the program that performs a specific function for the user

---

## USER STORIES

1.	Visitors can view a landing page with a description of the service and register to create an account.
2.	Visitors can respond to word-of-mouth or emailed invitations to register for an account, in order to view the Owner’s "album" and listed items (after being given access by the Owner).
3.	Upon registering successfully by entering account details (username, password, email, name, address, phone#), the User is directed to the application home page. Subsequent logins route to their profile page and display a welcome message and getting started tips.
4.	Users can edit their own User details (name, address, phone#, profile photo).
5.	Users can view and search listed albums and items by keyword, from the albums they have been given access to.
6.	Users can "bid" on item(s) by indicating their interest, by clicking an Interested button.
7.	Owners (a user who created/owns an album), can CRUD their own album(s) with title, description, lineage, deadline for responses, upload photo(s) and video(s).
8.	Owners (a user who created/owns an album), can CRUD their album's items, with title, description, lineage, upload photo(s) and video(s).
9.	Owners (a user who created/owns an album), will invite people to register on the site.
10.	Owners (a user who created/owns an album), can search for registered users by name or email address. The Owner can select the matching user to enable access.
11.	Owners (a user who created/owns an album), can view a dashboard with summary information by album: <br>
A.	a grid/matrix of items and users, with color-coded interest ranking <br>
B.	a list by item with a count of interested users, click to list the interested users for an item <br>
C.	a list by users associated with the owner’s album(s), with a count of items "bid" on/interested in, click to list the items for a user <br>

---

## FEATURE LIST 
### (Core functionality) 
##### Cross-referenced features to user-story#

*	Landing page with information describing the application, and a call to action (sign up). 1 
*	Registration/signup page to create an account (sign up). 1 
*	Upon successful signup and/or login, direct the user to their profile page and display a welcome message. 3
*	Dynamic navbar for login/logout and user roles (user vs. user/owner vs. admin) options. 3
*	Lock down the user profile page, allow the user to CRUD their own user account details. 4
*	Allow users to view the albums and listed items they have access to. 5 
*	Search functionality that allows users to search in the albums and listed items they have access to, by title, description, and/or lineage. 5
*	Indicate interest in an item, and save the user’s interest in the database. 6
*	Create an Album view page with links to each individual Album page. 7 
*	Allow the user/owner to create, update and delete albums. 7
*	Create an Item index page with links to each individual Item page. 8
*	Allow the user/owner to create, update and delete items. 8 
*	Implement the Filestack API for uploading photos and videos. 7, 8
*	Allow uploading photos and videos to albums and items. 7, 8
*	Create an admin/owner dashboard with color-coded summary info by album. 11, A/B/C

---

### Extra Features (Optional functionality)

* create an email invitation associated with an album, allow owner to enter a list of email addresses to send the invitation to. The link in the email is to the registration/signup page. 9
*	create a view to list the email invites sent, and the name of any registered user with a matching email address. The owner can select the matching user to enable access to that album. 10

##### Users
 
*	can comment on an item or reply to a comment.
*   can rank their interest on an item on a scale of 1 (low) to 3 (high).
*	can indicate if they are willing to pay shipping costs, if applicable.
*	can opt in to enable SMS text, and opt in/out of each type of notification:
1.	a new item added to an album.
2.	an item "won" (awarded to them).
3.	a new comment on an item they are interested in.
4.	a change in status on an item they are interested in.
5.	a new direct message in the application.
*	can upload an image for their profile photo.
*	can request a forgotten username or a password reset and receive an email.
*	can pay an invoice for shipping costs.

##### Owners (a User who owns an album)

*	can see invite history.
*	can send or re-send invitation(s) to individuals or groups, as needed.
*	can mark an item as "awarded" to a user, from the item listing and/or the dashboard matrix:
1.	available/awarded status is shown on the item listing.
2.	awarded status is shown on the color-coded dashboard matrix, marked with a symbol.
3.	an email is sent to the awarded user.
4.	an email is sent to the interested users who were not awarded.
*	can mark an item as "pick-up only".
*	can add estimated shipping costs to an item, if applicable.
*	can invoice a user for payment of shipping costs (total for multiple awarded items).

##### Admins

*	can CRUD all users, albums, and items.
*	can grant admin privileges to another user.
*	can create email message templates for use when messaging user(s).
*	can create SMS text message templates for use when messaging user(s).
*	can privately message a user or groups of users.

---
---

## URL METHODS MAP

#### Models (Beans):
	
* Album
* User -	May be a guest user and/or an owner	
* Item
* Image
* Interest
* Invite
* Comment - optional feature
* Admin - optional feature (add separate admins table/model with FK to user_id for admin users with top-level access to CRUD everything (for developers/application support)

---

#### Views (Navbar before Login)

| URL        | Description      | Navbar Name      | Thymeleaf template |
| :---       | :---             | :---             | :---               |
| /          | Landing page     | Home or Brand    | users/home.html    |
| /sign-up   | default          | Sign Up          | users/sign-up.html |
| /login     | default          | Login            | users/login.html   |
| /devs      | Developers       | Developers       | dev-profile.html   | 

#### Views (Navbar after Login)

| URL        | Description      | Navbar Name                | Thymeleaf template    |
| :---       | :---             | :---                       | :---                  |
| /          | same as before   | Home or Brand              | same as before login  |
| /albums     | Albums page     | Albums/Browse              | albums.html           |
| /view      | Summary page     | Interests                  | view.html             |
| /albums/create | Create new album | Create                 | /albums/create.html   |
| /devs      | same as before   | Developers                 | same as before login  |
| /profile   | User account     | User icon/photo (optional) | profile.html          |
| /logout    | default          | Logout                     | part of navbar        |

* NOTE - there will be individual albums/create and items/create pages, and same for edit and delete pages. The navbar "Browse" is currently replaced by "Albums" for developers benefit (may change it to "View" or "Browse" later?).

---

## METHODS

(Under construction... a work in progress!)
 
| Methods     | URL                 | Actions           | GET - template, POST - send/redirect Url | Wireframe filename |
| :---        | :---                | :---              | :---               | :---                |
| GET	      | /	                | Landing page      | home.html          |                    |
| GET    	  | /sign-up            | register form     | sign-up.html       |                    |
| POST	      | /sign-up            | save account      | redirect ?         |                    |
| GET	      | /login	            | login             | login.html         |                    |
| POST	      | /login	            | logged in         | redirect ?         |                    |
| GET         | /devs               | view devs         | dev-profile.html   |                    |
| GET	      | /view	            | view interests    | view.html          | WILLing Album View.pdf, WILLing Items Views.pdf, Pending new |
| continued   | continued           | interest icon     | see GET method /items/interest | |
| continued   | continued           | album, create item icon | see GET method /albums/{id}/create | |
| POST        | /view               | Awards?           | redirect /view ?   |               |
| GET         | /create             | Create album form | /albums/create.html   |               |
| POST        | /create             | Save album        | redirect:/albums{id}  |               |
| GET         | /albums             | Albums list view w/items (nested loop) | /albums/albums.html | |
| GET	      | /albums/{id}        | Single album view | /albums/album.html    |               |
| GET         | /albums/{id}/create | Create item form  | /items/create.html    | see below (w/items) for the POST method |
| GET         | /albums/{id}/edit   | edit form         | /albums/edit.html     |               |
| POST        | /albums/{id}/edit   | Save edit         | redirect /albums/{id} |               |
| GET	      | /albums/{id}/delete	| delete confirmation form  | /albums/delete.html | Album (owner) |
| POST        | /albums/{id}/delete | process the delete | redirect /view       |               |
| Note:       | the items listing will be on the albums page | | | |
| GET         | /items/{id}         | Single Item view, interest icon & ranking form | /items/item.html & interests.html ? | WILLing Items Views.pdf |
| POST        | /items/{id}/users/{id} | Save Interests ranking | redirect /items/{id} |         |
| POST        | /albums/{id}/create  | Save created item | redirect /albums/{id} | see albums for the GET method |
| GET         | /items/{id}/edit    | edit form          | /items/edit.html      |         |
| POST        | /items/{id}/edit    | Save edit          | redirect /items/{id}  |         |
| GET	      | /items/{id}/delete	| delete confirmation form | /items/delete.html   | WILLing Items Views.pdf |
| POST        | /items/{id}/delete   | Process delete item     | redirect /albums/{id} | |
| GET         | /profile/{id}        | view (User), dashboard (Owner) | profile.html    | WILLing Dashboard Album View.pdf, AdminMatrix and UserCrudPage.pdf |
| GET         | /profile/{id}/edit   | edit form user (self) details | profile-edit.html  | same as above |
| POST        | /profile/{id}/edit   | validate, save user details   | redirect /profile  | same as above |
| GET         | /profile/{id}/delete | delete user account, etc      | profile-delete.html | same as above |
| POST        | /profile/{id}/delete | confirm & process delete      | display message and redirect / | |
| GET/POST    | /profile/{id}/dashboard  | owner can award items to users | TBD  | |
| GET         | /interests          | interests - filter on view page | redirect /views | |
| GET         | /albums{id}/users ?  | a form/widget on album page to add/remove users (access) | TBD | |
| POST        | /albums/{id}/users ? | a process to add/update/delete the selected users | redirect /albums/{id} |
| GET         | /profile/{id}/invites     | a form to enter email addresses | profile-invites.html | |
| POST        | /profile/{id}/invites     | verify & process the list of emails | redirect to /profile | |
| Optional features | | | | | |
| GET         | /items/{id}/comment  | a form to comment/reply on item | /items/comment.html | |
| POST        | /items/{id}/comment  | save the comment                | redirect to /items/{id} | |
| more TBD    | | | | | |

---

## SECURITY AND PROFILE DASHBOARD EXAMPLES

##### Profile (everyone) - User account info (CRUD)				

--- 

##### Profile (Owner) - dashboard

Matrix: 
A grid layout referencing Items to Users (see wireframe)
				
Users list:

| Username  | First Name  | Last Name | #Interested (items) |
| :---      | :---        | :---      | :--- |
| kjohnson  | Kim         | Johnson   | 1    |
| ljames    | Leroy       | James     | 10   |
				
Albums list:

| Album title	| Item Title	      | #Interested (users) |
| :---          | :---                | :--- |
| Uncle Joe     | Cornhusker tool     | 1 |
| Grandma Sue   | China dishes        | 5 |
