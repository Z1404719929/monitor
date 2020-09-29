import pandas as pd
from pandas import DataFrame

#设置最大显示列数
pd.set_option('display.max_columns', 17)
#读取货物运输表
df=pd.read_csv('collection_freight.csv')

#查看数据行列数
print("查看数据行列数",df.shape)

#查看数据类型
print("查看数据类型",df.dtypes)

# 查看数据描述
print("查看数据描述",df.describe())

#查看每一列的缺失值数量
print("查看每一列的缺失值数量",df.isnull().sum())


#缺失值处理 机场名字，年份，某一个数据为空时，清除那一行  subset表示去重的列，默认None，表示所有列
df = df.dropna(subset=['Airportname','Year'])
# print("机场名等缺失值删除",df)

# 缺失值替换成0，年月一整年数据存在缺失时替换成0
df=df.fillna(0)
# print("数据缺失值替换",df[['January','February','March','April','May','June','July','August','September','October','November','December','Whole year']])

##############
# 坐标是Not found的行替换成(Decimal('-1'), Decimal('-1'))
# df=df[~(df['Airport coordinates'].isin(['Not found']))]
# df['Airportcoordinates']=df['Airportcoordinates'].replace(0,'Not found')
# df=df.replace('Not found',"(Decimal('-1'), Decimal('-1'))")
# print("替换坐标为Not found的值",df)
#################

#去除机场名与年重复的行
df=df.drop_duplicates(['Airportname','Year'])
print("删除机场名与年重复的行",df.size)

#清除一整年数据都为0的行
df=df[~(df['January'].isin([0])&df['February'].isin([0])&df['March'].isin([0])
        &df['April'].isin([0])&df['May'].isin([0])&df['June'].isin([0])
        &df['July'].isin([0])&df['August'].isin([0])&df['September'].isin([0])
        &df['October'].isin([0])&df['November'].isin([0])&df['December'].isin([0]))]
print("清除没有数据的行",df)


# 按'切割坐标，取出经纬度
df3=df['Airportcoordinates'].str.split('\'',expand=True)
# 去除多余列
df3=df3.drop([0,2,4],axis=1)
df=df.drop(['Airportcoordinates'],axis=1)
# 连接表
df=pd.merge(df,df3,how='left',left_index=True,right_index=True)
# 重命名取出的坐标
df.rename(columns={1:'longitude',3:'latitude'},inplace=True)
# 俄罗斯经度26-170 纬度41-82 过滤
df[['longitude','latitude']] = df[['longitude','latitude']].apply(pd.to_numeric)
df = df.loc[(df['longitude'] >= 26) & (df['longitude'] <= 170) & (df['latitude'] >= 41) & (df['latitude'] <= 82)]
print(df)


# 数据写入数据库airport，freight_data表
from pandas.io import sql
from sqlalchemy import create_engine
engine= create_engine('mysql+pymysql://root:123456@localhost:3306/airport?charset=utf8',echo = True)


#重写索引
# df.reset_index(drop=True, inplace=True)
# 写入
# 改变列名
df1=df
df1.rename(columns={'January':1,'February':2,'March':3,'April':4,'May':5,'June':6,'July':7,'August':8,'September':9,'October':10,'November':11,'December':12,},inplace=True)
df1=df1.melt(id_vars=["Airportname","Year","longitude","latitude"],
        value_vars=[1,2,3,4,5,6,7,8,9,11,12],
        var_name='month',
        value_name='value'
        )
print("列转换成行",df1)
stmt="delete from data_freight_all"
sql.execute(stmt,engine)
df1.to_sql('data_freight_all',con=engine,if_exists='append',index=False)
# 删除不需要的列
df1=df1.drop(["longitude","latitude"],axis=1)
#重写索引
# df1.reset_index(drop=True, inplace=True)
df1.to_csv('data_freight.csv',index=False)
df1.to_sql('data_freight',con=engine,if_exists='replace',index=False)


# 计算机场不重复的数量
# print(len(df.drop_duplicates(['Airport name'])))
# 计算机场与坐标不重复的数量
# print(len(df.drop_duplicates(['Airport name', 'Airport coordinates'])))

################################################
#重写索引
# df.reset_index(drop=True, inplace=True)
# 将机场名和坐标单独存一个表
df=df.drop_duplicates(['Airportname'])
df2=df[['Airportname','longitude','latitude']]
print(df2)
#重写索引
# df2.reset_index(drop=True, inplace=True)
df2.to_csv('air_freight.csv',index=False)
df2.to_sql('air_freight',con=engine,if_exists='replace',index=False)
#######################################################


##############################################################################################




#读取乘客人次表
df=pd.read_csv('collection_passenger.csv')

#查看数据行列数
print("查看数据行列数",df.shape)

#查看数据类型
print("查看数据类型",df.dtypes)

# 查看数据描述
print("查看数据描述",df.describe())

#查看每一列的缺失值数量
print("查看每一列的缺失值数量",df.isnull().sum())


#缺失值处理 机场名字，年份，某一个数据为空时，清除那一行  subset表示去重的列，默认None，表示所有列
df = df.dropna(subset=['Airportname','Year'])
# print("机场名等缺失值删除",df)

# 缺失值替换成0，年月一整年数据存在缺失时替换成0
df=df.fillna(0)
# print("数据缺失值替换",df[['January','February','March','April','May','June','July','August','September','October','November','December','Whole year']])

# 坐标是Not found的行替换成(Decimal('-1'), Decimal('-1'))
# df=df[~(df['Airport coordinates'].isin(['Not found']))]
df['Airportcoordinates']=df['Airportcoordinates'].replace(0,'Not found')
df=df.replace('Not found',"(Decimal('-1'), Decimal('-1'))")
print("替换坐标为Not found的值",df)

#去除机场名与年重复的行
df=df.drop_duplicates(['Airportname','Year'])
print("删除机场名与年重复的行",df)

#清除一整年数据都为0的行
df=df[~(df['January'].isin([0])&df['February'].isin([0])&df['March'].isin([0])
        &df['April'].isin([0])&df['May'].isin([0])&df['June'].isin([0])
        &df['July'].isin([0])&df['August'].isin([0])&df['September'].isin([0])
        &df['October'].isin([0])&df['November'].isin([0])&df['December'].isin([0]))]
print("清除没有数据的行",df)


# 重写索引
# df.reset_index(drop=True, inplace=True)
# 按'切割坐标，取出经纬度
df3=df['Airportcoordinates'].str.split('\'',expand=True)
# 去除多余列
df3=df3.drop([0,2,4],axis=1)
df=df.drop(['Airportcoordinates'],axis=1)
# 连接表
df=pd.merge(df,df3,how='left',left_index=True,right_index=True)
# 重命名取出的坐标
df.rename(columns={1:'longitude',3:'latitude'},inplace=True)
#俄罗斯经度26-170 纬度41-82 过滤
df[['longitude','latitude']] = df[['longitude','latitude']].apply(pd.to_numeric)
df = df.loc[(df['longitude'] >= 26) & (df['longitude'] <= 170) & (df['latitude'] >= 41) & (df['latitude'] <= 82)]
print(df)


# 数据写入数据库airport，freight_data表
from pandas.io import sql
from sqlalchemy import create_engine
engine= create_engine('mysql+pymysql://root:123456@localhost:3306/airport?charset=utf8',echo = True)


#重写索引
# df.reset_index(drop=True, inplace=True)
# 写入
# 改变列名
df1=df
df1.rename(columns={'January':1,'February':2,'March':3,'April':4,'May':5,'June':6,'July':7,'August':8,'September':9,'October':10,'November':11,'December':12,},inplace=True)
df1=df1.melt(id_vars=["Airportname","Year","longitude","latitude"],
        value_vars=[1,2,3,4,5,6,7,8,9,11,12],
        var_name='month',
        value_name='value'
        )
print("列转换成行",df1)
stmt="delete from data_passenger_all"
sql.execute(stmt,engine)
df1.to_sql('data_passenger_all',con=engine,if_exists='append',index=False)
# 删除不需要的列
df1=df1.drop(["longitude","latitude"],axis=1)
#重写索引
# df1.reset_index(drop=True, inplace=True)
df1.to_csv('data_passenger.csv',index=False)
df1.to_sql('data_passenger',con=engine,if_exists='replace',index=False)


# 计算机场不重复的数量
# print(len(df.drop_duplicates(['Airport name'])))
# 计算机场与坐标不重复的数量
# print(len(df.drop_duplicates(['Airport name', 'Airport coordinates'])))

################################################
#重写索引
# df.reset_index(drop=True, inplace=True)
# 将机场名和坐标单独存一个表
df=df.drop_duplicates(['Airportname'])
df2=df[['Airportname','longitude','latitude']]
print(df2)
#重写索引
# df2.reset_index(drop=True, inplace=True)
df2.to_csv('air_passenger.csv',index=False)
df2.to_sql('air_passenger',con=engine,if_exists='replace',index=False)
#####################################################









































# 查找含中文乱码的数据
# reg='[\u4e00-\u9fa5]'
# df1=df['Airport name'].str.contains(reg)
# print(df1)
# df=df[~df1]
# print(df)