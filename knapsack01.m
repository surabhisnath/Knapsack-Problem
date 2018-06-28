function [best, items] = knapsack01(weights, values, W)
    [M, N] = size(weights);
    weights = weights(:);
     values = values(:);

    A = zeros(length(weights)+1,W+1);

    for j = 1:length(weights)
        for Y = 1:W
            if weights(j) > Y
                A(j+1,Y+1) = A(j,Y+1);
            else
                A(j+1,Y+1) = max( A(j,Y+1), values(j) + A(j,Y-weights(j)+1));
            end
        end
    end

   best = A(end,end);
   
   items = zeros(length(weights),1);
   a = best;
   j = length(weights); 
   Y = W;
   while a > 0
       while A(j+1,Y+1) == a
           j = j - 1;
       end
       j = j + 1; %This item has to be in the knapsack
       items(j) = 1;
       Y = Y - weights(j);
       j = j - 1;
       a = A(j+1,Y+1);
   end
   
    items = reshape(items,M,N);
end


