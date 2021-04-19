package jp.techacademy.thion.maikeru.apiapp2


interface FragmentCallback {
    // Itemを押したときの処理

    // お気に入り追加時の処理
    fun onAddFavorite(shop: Shop)
    // お気に入り削除時の処理
    fun onDeleteFavorite(id: String)

    fun onClickItem1(shop:Shop)
    fun onClickItem2(favoriteShop: FavoriteShop)
}