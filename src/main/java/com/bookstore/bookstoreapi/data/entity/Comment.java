package com.bookstore.bookstoreapi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.text.SimpleDateFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "CommentID")
  private Long id;

  @Column(name = "Timestamp")
  @Builder.Default
  private String timeStamp =
      new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date());

  @Column(name = "Comment")
  private String comment;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "BookID")
  private Book book;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "UserID")
  private User user;
}
