package com.kameloon.trialtask.model;

/**
 * Модель количества лайков и дизлайков
 *
 * @param likes    количество лайков
 * @param dislikes количество дизлайков
 */
public record VoteModel(int likes, int dislikes) {
}