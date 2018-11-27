-- users in system
insert into account(account_name,password) values('tcs', 'london');
insert into account(account_name,password) values('jlr', 'europe');



-- oauth client details
insert into client_details(client_id,client_secret,resource_ids,scopes,grant_types,authorities)
values('tcs','tcssecret',null,'openid,read','authorization_code,refresh_token,password',null);