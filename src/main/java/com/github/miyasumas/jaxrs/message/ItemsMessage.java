package com.github.miyasumas.jaxrs.message;

import java.util.LinkedList;
import java.util.List;

/**
 * 複数項目を返却するメッセージ
 * @author MIYASAKA Yasumasa
 * @since 2014/11/04
 */
public final class ItemsMessage<T> {

	/**
	 * 全件数
	 */
	private Integer total;

	/**
	 * アイテム
	 */
	public List<T> items;

	/**
	 * インスタンス化します。
	 * @param total 合計件数
	 * @param items アイテム
	 */
	private ItemsMessage(Integer total, List<T> items) {
		this.total = total;
		this.items = items;
	}

	public Integer getTotal() {
		return total;
	}

	public List<T> getItems() {
		return items;
	}

	/**
	 * ビルダ
	 * @author MIYASAKA Yasumasa
	 * @since 2014/11/04
	 * @param <T>
	 */
	public static class Builder<T> {

		/**
		 * 全件数
		 */
		private Integer total;

		/**
		 * アイテム
		 */
		private List<T> items = new LinkedList<>();

		/**
		 * 全件数を設定します。
		 * @param total 全件数
		 * @return このオブジェクト
		 */
		public Builder<T> total(int total) {
			this.total = total;
			return this;
		}

		/**
		 * アイテムを設定します。
		 * @param items アイテム
		 */
		public Builder<T> setItems(List<T> items) {
			this.items = items;
			return this;
		}

		/**
		 * アイテムを追加します。
		 * @param item アイテム
		 * @return このオブジェクト
		 */
		public Builder<T> addItem(T item) {
			this.items.add(item);
			return this;
		}

		/**
		 * ビルドします。
		 * @return メッセージ
		 */
		public ItemsMessage<T> build() {
			return new ItemsMessage<T>(total, items);
		}
	}
}
