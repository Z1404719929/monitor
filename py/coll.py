import pandas as pd

# 数据写入数据库
from pandas.io import sql
from sqlalchemy import create_engine
engine= create_engine('mysql+pymysql://root:123456@localhost:3306/airport?charset=utf8',echo = True)

# 货运表分析
df=pd.read_csv('data_freight.csv')

print(df)
# 走势图分析 按年份
df1=df.groupby(['Year']).sum()
print(df1)
df1=df1.drop(['month'],axis=1)
df1.to_csv('freightbyyear.csv')
df1.to_sql('freightbyyear',con=engine,if_exists='replace')

# 统计 按月份
df1=df.groupby(['month']).sum()
print(df1)
df1=df1.drop(['Year'],axis=1)
df1.to_csv('freightbymonth.csv')
df1.to_sql('freightbymonth',con=engine,if_exists='replace')

# 统计 按机场，年份
stmt="delete from freightbyairandyear"
sql.execute(stmt,engine)
df1=df.groupby(['Airportname','Year']).sum()
print(df1)
df1=df1.drop(['month'],axis=1)
df1.to_csv('freightbyairandyear.csv')
df1.to_sql('freightbyairandyear',con=engine,if_exists='append')

# 统计 按机场，月份
stmt="delete from freightbyairandmonth"
sql.execute(stmt,engine)
df1=df.groupby(['Airportname','month']).sum()
print(df1)
df1=df1.drop(['Year'],axis=1)
df1.to_csv('freightbyairandmonth.csv')
df1.to_sql('freightbyairandmonth',con=engine,if_exists='append')


# 客运表分析
df=pd.read_csv('data_passenger.csv')

print(df)
# 走势图分析 按年份
df1=df.groupby(['Year']).sum()
df1=df1.drop(['month'],axis=1)
df1.to_csv('passengerbyyear.csv')
df1.to_sql('passengerbyyear',con=engine,if_exists='replace')

# 统计 按月份
df1=df.groupby(['month']).sum()
df1=df1.drop(['Year'],axis=1)
df1.to_csv('passengerbymonth.csv')
df1.to_sql('passengerbymonth',con=engine,if_exists='replace')

# 统计 按机场，年份
stmt="delete from passengerbyairandyear"
sql.execute(stmt,engine)
df1=df.groupby(['Airportname','Year']).sum()
df1=df1.drop(['month'],axis=1)
df1.to_csv('passengerbyairandyear.csv')
df1.to_sql('passengerbyairandyear',con=engine,if_exists='append')

# 统计 按机场，月份
stmt="delete from passengerbyairandmonth"
sql.execute(stmt,engine)
df1=df.groupby(['Airportname','month']).sum()
df1=df1.drop(['Year'],axis=1)
df1.to_csv('passengerbyairandmonth.csv')
df1.to_sql('passengerbyairandmonth',con=engine,if_exists='append')






