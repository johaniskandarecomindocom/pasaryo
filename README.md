login
home - tambah product
keranjang - pembayaran
summary transaksi
user profil - point = 5% dr total pesanan pesanan

==USER== --> service : user

user/login
POST
BODY : username
       password
RESPONSE :  statusCode,
			message,
			token


user/get/{id}
GET
RESPONSE : statusCode,
           message,
		   result : {
		   name,
		   email,
		   point,
		   address
		}

user/logout
POST
RESPONSE : statusCode,
		   message


==PRODUCT== --> service : product

product/get
GET 
PARAMETER : productName
RESPONSE : statusCode,
		   message,
		   result : [
	id,
	productName,
	description,
	price : 50000 (string),
	discountPrice : 30000 (string),
	stock : int,
	image : link
]
confirm --> pagination?


product/getbanner
GET
RESPONSE : statusCode,
		   message,
		   result :[
	link images
]

==CART== --> service : order

cart/checkout
POST
BODY : address,
	   products : [
		  id,
		  quantity : int
	   ],
	   subTotal : string,
	   discount : string,
	   deliveryFee : string,
	   total : string
RESPONSE : statusCode,
		   message

==ORDER== --> service : order

order/get
GET
PARAMETER : productName --> confirm dulu
RESPONSE : statusCode,
		   message,
		   result : [
			  id,
			  checkoutDate : TIMESTAMP,
			  products : int,
			  total : string,
			  paymentMethod : hardcode cash,
			  status : diproses, dikirim, selesai
		   ]
		   
order/get/{id}
GET
RESPONSE : statusCode,
		   message,
		   result : {
			  status,
			  address,
			  subTotal : string,
			  discount : string,
	          deliveryFee : string,
	          total : string
			  products : [
			     productName,
				 price,
				 id,
				 image : link
			  ]
		   }
		   
order/finishorder/{id}
GET
RESPONSE : statusCode
		   message
		   

========= DATABASE ============

USER
	id
	username
	password
	fullname
	email
	address
	point
	image : string
	
-----------------------	

PRODUCT
	id
	productName
	description
	price
	discountPrice
	stock
	image
	
BANNER
    id
	image

------------------------
	
ORDER
	id
	orderDate
	paymentMethod
	subTotal
	total
	deliveryFee
	discount
	status
	address

ORDER_DETAILS
	id
	orderId
	productId
	productName
	description
	price
	
	
==== SERVICES ====

stephanus
USER --> database : user

dwiki
PRODUCT --> database :  product & banner

johan
ORDER --> database : order & order details



service ORDER -----> kafka topic-user {userid: xxxxxxx, point: 10}
											|
service USER  subscribe topic-user  <-------|
  get point where id = xxxxxxx
  point + point dr message
  update point
  
selesaikan pesanan = ganti status order dgn id jadi selesai, kalkulasi poin

diproses -> dikirim = pakai job setiap 5 menit







