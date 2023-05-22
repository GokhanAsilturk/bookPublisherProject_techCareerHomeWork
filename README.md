# bookPublisherProject Requirements (in english)
Imagine that you are a software developer working for a publishing house called "Ink Pen Books". One day, the company said to a new writer,
The talented young writer receives a manuscript from Alice. The manuscript is a novel called "The Hidden Garden" and a young girl who discovers 
a secret garden in her new home.
He talks about learning the power of nature and friendship.

Your task, as Software Developer, is to create a Spring Boot application that will help "Ink Pen Books" publish Alice's novel.
Specifically, you need to create a backend API that allows the company to store information about books, authors, and sales.

The API has these requirements:

1-) The API should allow the company to create a new bookEntity record in the database and this record should include the bookEntity's title, 
description, publication date and authorEntity information (name, email and bio).
 
2-) API should allow "Ink Pen Books" to retrieve a bookEntity record by its ID and get a list of all books in the database.

3-) The API should allow "Ink Pen Books" to update the information of a bookEntity record including the authorEntity's information.
 
4-) API should allow "Ink Pen Books" to delete a bookEntity record from database.

5-) The API should properly handle all request errors and provide informative error messages to the client.
To implement these requirements you will need to use MongoDB as the database and implement common error handling methods for all points. 
You should also include appropriate tests to verify that the API is working as expected.

As a bonus, you can add additional features like tracking bookEntity sales, generating reports or userEntity authentication.

# bookPublisherProject Requirements (in turkish)
Hayal edin ki, "Mürekkep Kalem Kitapları" adlı bir yayınevinde çalışan bir yazılım geliştiricisisiniz. Bir gün, şirket yeni bir yazardan, 
yetenekli genç yazar Alice'den bir el yazması alır. El yazması, "Saklı Bahçe" adlı bir roman ve yeni evinde gizli bir bahçe keşfeden genç bir kızın 
doğa ve arkadaşlık gücünü öğrenmesini anlatıyor.

Göreviniz, Yazılım Geliştirici olarak, "Mürekkep Kalem Kitapları"nın Alice'in romanını yayınlamasına yardımcı olacak bir Spring Boot uygulaması oluşturmaktır. 
Özellikle, şirketin kitap, yazar ve satışlar hakkında bilgi saklayabilmesine olanak tanıyan bir arka uç API'si oluşturmanız gerekiyor.

API için şu gereksinimler vardır:

1-) API, şirketin veritabanına yeni bir kitap kaydı oluşturmasına izin vermeli ve bu kayıt, kitabın başlığı, 
açıklaması, yayın tarihi ve yazar bilgilerini (ad, e-posta ve bio) içermelidir.
 
2-) API, bir kitap kaydını ID'sine göre almak ve veritabanındaki tüm kitapların bir listesini almak için "Mürekkep Kalem Kitaplarına" izin vermelidir.

3-) API, yazarın bilgilerini de içeren bir kitap kaydının bilgilerini güncellemek için "Mürekkep Kalem Kitaplarına" izin vermelidir.
 
4-) API, bir kitap kaydını veritabanından silmek için "Mürekkep Kalem Kitaplarına" izin vermeli.

5-) API, tüm istek hatalarını düzgün bir şekilde ele almalı ve istemciye bilgilendirici hata mesajları sağlamalıdır.
Bu gereksinimleri uygulamak için veritabanı olarak MongoDB kullanmanız ve tüm noktalar için genel hata işleme yöntemleri uygulamanız gerekecek. 
Ayrıca, API'nin beklenildiği gibi çalıştığını doğrulamak için uygun testleri de dahil etmelisiniz.

Bir bonus olarak, kitap satışlarını takip etme, raporlar oluşturma veya kullanıcı kimlik doğrulaması gibi ek özellikler ekleyebilirsiniz.

