# Library Management System

## 簡介
這是一個用Java開發的圖書館管理系統，系統使用者分為未登入者、讀者、圖書館職員以及管理者。不同使用者權限將有不同之功能。系統包含基本書籍、讀者、出版社、借閱等管理功能，以及額外功能如活動管理、輪播展示、Email寄信通知等等。

## 前後端開發架構
![image](4.jpg)
### 關於Spring Boot
Spring Boot是一個建立在Spring框架之上的開源專案,旨在簡化Spring應用程式的初始搭建和開發過程。它的主要特點包括:
1. 自動配置 (Auto Configuration)：Spring Boot會自動根據你加入的jar包來配置應用程式,無需進行過多手動設定。
2. 內嵌的Tomcat、Jetty或Undertow容器：Spring Boot內嵌了常用的servlet容器,無需部署到外部容器中。
3. 啟動器 (Starter)：提供一系列方便的啟動器(Starter),只需在專案中加入對應啟動器,即可獲得所需的所有相關依賴。
4. 監控和管理：Spring Boot專案內建了actuator模組,可以用來監控應用程式的運行情況。
5. 外部化配置：允許我們將配置信息寫在不同的地方,例如本地文件、環境變量等。

### 關於Thymeleaf
Thymeleaf是一種用於Web和非Web環境的modern server-side Java template engine。它的主要特點包括:
1. 靜態原型：Thymeleaf可以生成靜態原型頁面,方便前端和後端並行開發。
2. 自然模板：Thymeleaf使用一種非常簡潔的語法,模板能夠直觀表達所需要的邏輯。
4. 響應式：Thymeleaf模板可以響應單個屬性的更改,而不需重新渲染整個頁面。
5. 國際化：Thymeleaf提供了對於國際化(i18n)資源的內建支持。
6. Java集成：Thymeleaf提供了對JaveScript和Java Bean的支持。
7. 模塊化：Thymeleaf支持通過佈局定義器(Layout Decorator)和佈局管理器(Layout Manager)實施模板繼承。
8. 安全性：Thymeleaf提供了防止常見Web漏洞(如XSS)的機制。

在Spring Boot中,Thymeleaf是Web模板引擎的常見選擇。它簡單易用,與Spring框架的集成自然無縫。許多開發者喜歡使用Thymeleaf來開發現代化的、響應式的Web應用界面。 

### 關於Hibernate
Hibernate是一個開源的對象關係映射(ORM)框架,它為Java語言提供了對象持久化的解決方案。使用Hibernate可以讓開發者採用面向對像的思維來操作關系型資料庫,而不用直接編寫大量的SQL語句。以下是Hibernate的一些主要特點:
1. 輕量級：Hibernate是一個輕量級的框架,需要很少的配置就可以運行。
2. 資料庫無關性：Hibernate支援多種關係型資料庫,如MySQL、Oracle、SQL Server等,開發者無需關心底層資料庫的差異。
3. 查詢語言：Hibernate提供了HQL(Hibernate查詢語言),類似SQL但面向對像,開發者也可使用原生SQL查詢。
4. 緩存機制：Hibernate內建了一級和二級緩存機制,提高了系統的性能。
5. 對象狀態管理：Hibernate會自動跟蹤對象的創建、修改和刪除狀態,并根據狀態生成適當的SQL語句。
6. 關係映射：Hibernate通過映射文件或註解的方式,將Java對象與資料庫表建立映射關係。

總之,Hibernate框架屏蔽了許多底層的數據持久化操作,大大簡化了JDBC的繁瑣編程。它是幾乎所有Java企業級應用都會使用的ORM框架之一。

## 開發輔助工具
![image](5.jpg)
