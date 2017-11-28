# Tugas 2 IF3110 Pengembangan Aplikasi Berbasis Web

## Kelompok Cisco Systems
## Anggota Tim
 - Devin Alvaro | 13515062
 - Dicky Novanto | 13515134
 - Francisco Kenandi Cahyono | 13515140
 
### Penjelasan
1. **Basis data dari sistem yang dibuat**
Terdapat 2 basis data yang digunakan yakni db_IDENTITY_SERVICE dan db_OJEKSERVICE. _Database_ db_IDENTITY_SERVICE terdiri dari 2 tabel yaitu tabel user(id,username,name,phone,email,password,isDriver,profilePicture) dan tabel user_with_token(id,token,expiry_date). _Database_ db_IDENTITY_SERVICE di-_handle_ oleh servlet dan utamanya digunakan pada layanan _Identity Service_ sedangkan _Database_ db_OJEK_SERVICE utamanya di-_handle_ oleh Jax-WS dan digunakan pada layanan _Ojek Service_. 

2. **Konsep _shared session_ dengan menggunakan REST**
Konsep _shared session_ dengan menggunakan REST berangkat dari sifat HTTP yang _stateless_. Tetapi sudah menjadi kebutuhan dari _web development_ bahwa state itu dibutuhkan. Salah satu jawaban dari kebutuhan tersebut adalah _session_. Umumnya pada implementasi _web service_ dan _web client_, _session_ disimpan pada _web service_, bukan _client_. _Server_ tidak menyimpan _session_. Dengan ini _server_ dapat melayani kebutuhan banyak _client_ dalam satu waktu. Dalam implementasi REST, seluruh state di-_transfer_ kemana-mana, tidak disimpan pada server. Itulah yang dimaksud dengan istilah _shared session_.

3. **Pembangkitan _token_ dan _expire time_ pada sistem yang anda buat**
Ketika pengguna melakukan _log in_ dan lolos validasi _username_ dan _password_, maka obyek AccessToken akan diinisialisasi. Obyek _AccessToken_ itu sendiri memiliki 2 buah atribut yaitu token (_string_) dan expiryTime (_Date_). Ketika obyek ini diinialisasi maka token akan diisi dengan UUID sedangkan expiryTime diisi dengan _Date_ saat ini.   

4. **Kelebihan dan kelemahan dari arsitektur aplikasi tugas ini, dibandingkan dengan aplikasi monolitik (_login_, CRUD DB, dll jadi dalam satu aplikasi)**
    * Lebih aman karena perpindahan antar halaman tidak dilakukan dengan _method_ GET dan parameter id tidak ditampilkan pada link sehingga pengguna tidak dapat mengubah akun di tengah jalan. Pada arsitektur lama, bila pengguna ingin masuk ke dalam suatu halaman dengan akun lain maka ia hanya perlu mengubah 
    * Keamanan juga lebih terjamin dengan sistema _expired time_.
    * Web service bersifat independen sehingga seharusnya ia bisa melayani banyak reuqest dari web application

### Pembagian Tugas

REST :
1. Login = 13515062, 13515134
2. Logout = 13515062
3. Sign up = 13515062

SOAP :
1. HistoryHide : 13515134
2. CompleteOrder : 13515062
3. SaveOrder : 13515062
4. SelectDriver : 13515062
5. AddLocation : 13515062 
6. DeleteLocation : 13515062
7. UpdateLocation : 13515062
8. UpdateProfile : 13515062
9. HistoryData : 13515134
10. OrderData : 13515062
11. ProfileData : 13515134
12. WebService : 13515062, 13515134
13. WebServiceDbConnection : 13515134

Other web Service:
1. AccessToken : 13515062, 13515134
2. IdentityServiceDbConnection : 13515134
3. User : 13515062, 13515134
4. UserInfo : 13515134
5. PojoList : 13515062, 13515134

Web app (JSP) :
1. history-driverhistory.jsp = 13515062, 13515134
2. history-orderhistory.jsp = 13515134, 13515140 
3. index.jsp = 13515134
4. login.jsp = 13515062
5. order-complete.jsp = 13515062, 13515140 
6. order-selectdestination.jsp = 13515062
7. order-selectdriver.jsp = 13515062, 13515140 
8. profile.jsp = 13515134
9. profile-editlocation.jsp = 13515134
10. profile-editprofile.jsp = 13515062
11. signup.jsp = 13515062

Others :
1. _Database_ = 13515062, 13515134, 13515140
2. Readme = 13515140